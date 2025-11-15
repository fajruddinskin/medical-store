// =======================================================
// 1) START LAB TEST FORM SUBMIT (ADD TEST)
// =======================================================
$(document).ready(function () {

    // -------------------------------
    //  GLOBAL ELEMENTS
    // -------------------------------
    const form = document.getElementById("labTestForm");
    const messageDiv = document.getElementById("labTestMessage");
    const tableBody = document.getElementById("labTestsTableBody");
    const testNameInput = document.getElementById("testName");
    const suggestionsList = document.getElementById("testSuggestions");

    // =======================================================
    // 1) LAB TEST FORM SUBMIT (ADD TEST)
    // =======================================================
    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const testData = {
            name: $("#testName").val().trim(),
            description: $("#testDescription").val().trim(),
            price: parseFloat($("#testPrice").val()),
            referrerFee: parseFloat($("#testReferrerFee").val()) || 0
        };

        if (!testData.name || isNaN(testData.price) || testData.price <= 0) {
            messageDiv.innerHTML =
                `<div class="alert alert-warning">⚠️ Please fill required fields properly.</div>`;
            return;
        }

        messageDiv.innerHTML = `<div class="alert alert-info">Saving...</div>`;

        try {
            const response = await fetch("/api/lab-tests", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(testData)
            });

            if (!response.ok) {
                const errText = await response.text();
                messageDiv.innerHTML = `<div class="alert alert-danger">❌ Failed: ${errText}</div>`;
                return;
            }

            const newTest = await response.json();

            messageDiv.innerHTML =
                `<div class="alert alert-success">✅ Lab Test "${newTest.name}" added successfully!</div>`;

            form.reset();

            addTestRow(newTest);

            const collapseEl = document.getElementById("labTestFormCollapse");
            if (collapseEl) {
                const bsCollapse = bootstrap.Collapse.getOrCreateInstance(collapseEl);
                bsCollapse.hide();
            }

        } catch (err) {
            console.error("Error saving test:", err);
            messageDiv.innerHTML =
                `<div class="alert alert-danger">⚠️ Server error. Try again.</div>`;
        }
    });

    function addTestRow(test) {
        const row = `
            <tr>
                <td>${test.id}</td>
                <td>${test.name}</td>
                <td>${test.description || "-"}</td>
                <td>$${test.price.toFixed(2)}</td>
                <td>$${(test.referrerFee || 0).toFixed(2)}</td>
            </tr>
        `;
        tableBody.insertAdjacentHTML("afterbegin", row);
    }

    // =======================================================
    // 2) AUTO-SUGGEST FOR TEST NAME
    // =======================================================
    let suggestionTimeout;

    testNameInput.addEventListener("input", function () {
        const query = this.value.trim();
        clearTimeout(suggestionTimeout);

        if (!query) {
            hideSuggestions();
            return;
        }

        suggestionTimeout = setTimeout(() => {
            $.ajax({
                url: "/api/lab-tests/search",
                method: "GET",
                data: { searchTerm: query },
                success: showSuggestions,
                error: hideSuggestions
            });
        }, 300);
    });

    function showSuggestions(tests) {
        suggestionsList.innerHTML = "";

        if (!tests || tests.length === 0) {
            hideSuggestions();
            return;
        }

        tests.forEach(test => {
            const li = document.createElement("li");
            li.classList.add("list-group-item", "list-group-item-action");
            li.textContent = test.name;
            li.style.cursor = "pointer";

            li.addEventListener("click", () => {
                $("#testName").val(test.name);
                $("#testDescription").val(test.description || "");
                $("#testPrice").val(test.price ?? "");
                $("#testReferrerFee").val(test.referrerFee ?? "");

                hideSuggestions();
            });

            suggestionsList.appendChild(li);
        });

        suggestionsList.style.display = "block";
    }

    function hideSuggestions() {
        suggestionsList.style.display = "none";
        suggestionsList.innerHTML = "";
    }

    document.addEventListener("click", function (e) {
        if (!testNameInput.contains(e.target) &&
            !suggestionsList.contains(e.target)) hideSuggestions();
    });

    // =======================================================
    // 3) LAB TEST TABLE SEARCH
    // =======================================================
    $("#testSearchBtn").on("click", e => {
        e.preventDefault();
        performSearch();
    });

    $("#testSearchInput").on("keypress", e => {
        if (e.which === 13) performSearch();
    });

    function performSearch() {
        const searchTerm = $("#testSearchInput").val().trim();

        if (!searchTerm) {
            alert("Enter a search term.");
            return;
        }

        $.ajax({
            url: "/api/lab-tests/search",
            method: "GET",
            data: { searchTerm },
            success: renderResults,
            error: () => {
                $("#labTestsTableBody").html(
                    `<tr><td colspan="5" class="text-center text-danger">Error fetching tests.</td></tr>`
                );
            }
        });
    }

    function renderResults(data) {
        const tbody = $("#labTestsTableBody");
        tbody.empty();

        if (!data || data.length === 0) {
            tbody.html(`<tr><td colspan="5" class="text-center text-muted">No results found</td></tr>`);
            return;
        }

        data.forEach(test => {
            tbody.append(`
                <tr>
                    <td>${test.id || "-"}</td>
                    <td>${test.name || "-"}</td>
                    <td>${test.description || "-"}</td>
                    <td>${test.price ?? "-"}</td>
                    <td>${test.referrerFee ?? "-"}</td>
                </tr>
            `);
        });
    }
});
// =======================================================
// 1) END LAB TEST FORM SUBMIT (ADD TEST)
// =======================================================

// =======================================================
// 2) START CREATE PATIENT
// =======================================================
async function createPatient() {
    // Get form values
    const name = document.getElementById("patientName").value.trim();
    const phone = document.getElementById("patientPhone").value.trim();
    const email = document.getElementById("patientEmail").value.trim();
    const history = document.getElementById("medicalHistory").value.trim();
    const age = document.getElementById("age").value.trim();
    const gender = document.getElementById("gender").value;

    const patientMessageDiv = document.getElementById("patientMessage");

    // Basic validation
    if (!name || !phone) {
        patientMessageDiv.innerHTML = `
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ⚠️ Please enter Patient Name and Phone Number.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>`;
        return;
    }

    const patientData = {
        name: name,
        phoneNumber: phone,
        email: email || null,
        medicalHistory: history || null,
        age: age || null,
        gender: gender || null
    };

    // Show loading message
    patientMessageDiv.innerHTML = `
        <div class="alert alert-info alert-dismissible fade show" role="alert">
            ⏳ Creating patient...
        </div>`;

    try {
        const response = await fetch("/api/patients", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(patientData)
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            throw new Error(errorData.message || "Failed to create patient");
        }

        const createdPatient = await response.json();

        patientMessageDiv.innerHTML = `
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ✅ Patient "${createdPatient.name}" created successfully!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>`;

        // Reset form
       // document.getElementById("patientForm").reset();

    } catch (error) {
        console.error("Error creating patient:", error);
        patientMessageDiv.innerHTML = `
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ❌ ${error.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>`;
    }
}
// =======================================================
// 2) END CREATE PATIENT
// =======================================================
