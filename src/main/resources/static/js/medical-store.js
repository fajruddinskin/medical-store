// =======================================================
// 1) START LAB TEST FORM SUBMIT (ADD TEST)
// =======================================================
$(document).ready(function () {

// Save Patient button reference
const savePatientBtn = document.getElementById("savePatientBtn");

// Fields that trigger re-enable when edited
const patientFormFields = [
    "patientName",
    "patientPhone",
   // "patientEmail",
    "medicalHistory",
    "age",
    "gender"
].map(id => document.getElementById(id));

// Enable Save Patient button if any field changes
patientFormFields.forEach(field => {
    if (!field) return;
    field.addEventListener("input", () => {
        savePatientBtn.disabled = false;
    });
});


    // -------------------------------
    //  GLOBAL ELEMENTS
    // -------------------------------
    const form = document.getElementById("labTestForm");
    const tableBody = document.getElementById("labTestsTableBody");
    const testNameInput = document.getElementById("testName");
    const suggestionsList = document.getElementById("testSuggestions");

    // =======================================================
    // 1) LAB TEST FORM SUBMIT (ADD TEST)
    // =======================================================
    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const testData = {
            id: $("#testId").val() || null,
            name: $("#testName").val().trim(),
            search: $("#search").val().trim(),
            description: $("#testDescription").val().trim(),
            price: parseFloat($("#testPrice").val())
        };
       var containerId= $("#containerId").val() || null;
       let finalUrl = containerId ? `/api/add-test/${containerId}` : `/api/add-test/ABC123`;
        if (!testData.name || isNaN(testData.price) || testData.price <= 0) {

            return;
        }

        try {
            const response = await fetch(finalUrl, {
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
           $("#containerId").val(newTest.id);
           $("#patientId").val(newTest?.patient?.id ?? "");
           $("#userName").text(newTest?.patient?.name ?? "");
            form.reset();
            addTestRow(newTest);

            const collapseEl = document.getElementById("labTestFormCollapse");
            if (collapseEl) {
                const bsCollapse = bootstrap.Collapse.getOrCreateInstance(collapseEl);
                bsCollapse.hide();
            }

        } catch (err) {
            console.error("Error saving test:", err);
        }
    });

    function addTestRow(data) {
        const tbody = document.getElementById("labTestsTableBody");
        tbody.innerHTML = ""; // Clear previous rows

        // Loop through all lab tests
        data.labTests.forEach(test => {
            const row = `
                <tr>
                    <td>${test.id}</td>
                    <td>${test.name}</td>
                    <td>
                        <button class="btn btn-sm btn-danger"
                         onclick="deleteTest('${data.id}', '${test.id}')"><i class="fas fa-trash"></i> Delete
                        </button>
                     </td>
                    <td>$${Number(test.price).toFixed(2)}</td>
                </tr>
            `;
            tbody.insertAdjacentHTML("beforeend", row);
        });

        // Display subtotal (assuming you have an element for it)
        const subtotalEl = document.getElementById("subTotal");
        const totalEl = document.getElementById("total");

        if (subtotalEl) {
            subtotalEl.textContent = "₹" + Number(data.subTotal).toFixed(2);
        }
        if (totalEl) {
           totalEl.textContent = "₹" + Number(data.total).toFixed(2);
         }
    }


    // =======================================================
    // 2) AUTO-SUGGEST FOR TEST NAME
    // =======================================================
    let suggestionTimeout;

    testNameInput.addEventListener("input", function () {
        $("#testId").val("")
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
                $("#testId").val(test.id);
                $("#testDescription").val(test.description || "");
                $("#testPrice").val(test.price ?? "");
                $("#search").val(test.search);

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
    const tableBody = document.getElementById("labTestsTableBody");
    //const email = document.getElementById("patientEmail").value.trim();
    const history = document.getElementById("medicalHistory").value.trim();
    const age = document.getElementById("age").value.trim();
    const gender = document.getElementById("gender").value;

    // Basic validation
    if (!name || !phone) {
        return;
    }

    const patientData = {
        id: $("#patientId").val() || null,
        name: name,
        phoneNumber: phone,
       // email: email || null,
        medicalHistory: history || null,
        age: age || null,
        gender: gender || null
    };


var containerId= $("#containerId").val() || null;
let finalUrl = containerId ? `/api/patients/create/${containerId}` : `/api/patients/create/ABC123`;

    try {
        const response = await fetch(finalUrl, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(patientData)
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            throw new Error(errorData.message || "Failed to create patient");
        }

        const createdPatient = await response.json();
        $("#containerId").val(createdPatient.id);
        $("#patientId").val(createdPatient?.patient?.id ?? "");
        $("#userName").text(createdPatient?.patient?.name ?? "");

        addTestRow(createdPatient);
        // Reset form
       // document.getElementById("patientForm").reset();
        savePatientBtn.disabled = true;

    } catch (error) {
        console.error("Error creating patient:", error);
    }

    function addTestRow(data) {
        const tbody = $("#labTestsTableBody");
              tbody.empty();
        data.labTests.forEach(test => {
                const row = `
                    <tr>
                        <td>${test.id}</td>
                        <td>${test.name}</td>
                        <td>
                            <button class="btn btn-sm btn-danger"
                                onclick="deleteTest('${data.id}', '${test.id}')"><i class="fas fa-trash"></i> Delete
                            </button>
                         </td>
                        <td>$${Number(test.price).toFixed(2)}</td>
                    </tr>
                `;
                tbody.append(row);
            });
        }
}
// =======================================================
// 2) END CREATE PATIENT
// =======================================================

// =======================================================
// 3) START DELIVERY DATE
// =======================================================
document.getElementById("deliveryDate").addEventListener("change", function () {
    const containerId = document.getElementById("containerId").value;  // reuse existing ID
    const deliveryDate = this.value;

    if (!deliveryDate) return;

    const payload = {
        containerId: containerId,
        deliveryDate: deliveryDate
    };

    fetch("/api/delivery/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
    .then(res => res.json())
    .then(data => {
        console.log("Delivery saved:", data);
    })
    .catch(err => console.error(err));
});
// =======================================================
// 3) END DELIVERY DATE
// =======================================================

// =======================================================
// 4) START DISCOUNT VALUE
// =======================================================
document.getElementById("discountValue").addEventListener("change", function () {
    const containerId = document.getElementById("containerId").value;  // existing hidden field
    const discountValue = this.value;

    if (!discountValue) return;

    const payload = {
        containerId: containerId,
        discount: discountValue
    };

    fetch("/api/discount/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    })
    .then(res => res.json())
    .then(data => {
        console.log("Discount saved:", data);

        const subtotalEl = document.getElementById("subTotal");
        const totalEl = document.getElementById("total");

        if (subtotalEl) {
         subtotalEl.textContent = "₹" + Number(data.subTotal).toFixed(2);
         }
        if (totalEl) {
        totalEl.textContent = "₹" + Number(data.total).toFixed(2);
         }

    })
    .catch(err => console.error(err));
});
// =======================================================
// 4) END DISCOUNT VALUE


document.addEventListener("DOMContentLoaded", function () {
    const el = document.getElementById("someId");

    if (el) {
        el.addEventListener("click", function () {
            // your code
        });
    }
});

// =======================================================
// DELETE TEST FUNCTION (GET METHOD, TABLE UPDATE LIKE ADD TEST)
// =======================================================
async function deleteTest(containerId, testId) {
    if (!confirm("Are you sure you want to delete this test?")) return;

    try {
        // Fetch updated container data from server
        const response = await fetch(`/api/delete/${containerId}/${testId}`, { method: "DELETE" });

        if (!response.ok) {
            throw new Error("Failed to delete test");
        }

        const updatedData = await response.json();

        // Update container & patient info
        $("#containerId").val(updatedData.id);
        $("#patientId").val(updatedData?.patient?.id ?? "");
        $("#userName").text(updatedData?.patient?.name ?? "");

        // Clear previous table rows
        const tbody = document.getElementById("labTestsTableBody");
        tbody.innerHTML = "";

        // Re-populate table like addTestRow
        updatedData.labTests.forEach(test => {
            const row = `
                <tr>
                    <td>${test.id}</td>
                    <td>${test.name}</td>
                    <td>
                        <button class="btn btn-sm btn-danger"
                            onclick="deleteTest('${updatedData.id}', '${test.id}')">
                            <i class="fas fa-trash"></i> Delete
                        </button>
                    </td>
                    <td>$${Number(test.price).toFixed(2)}</td>
                </tr>
            `;
            tbody.insertAdjacentHTML("beforeend", row);
        });

        // Update subtotal & total
        const subtotalEl = document.getElementById("subTotal");
        const totalEl = document.getElementById("total");

        if (subtotalEl) {
            subtotalEl.textContent = "₹" + Number(updatedData.subTotal).toFixed(2);
        }
        if (totalEl) {
            totalEl.textContent = "₹" + Number(updatedData.total).toFixed(2);
        }

        // Disable Save Patient button (optional)
        //savePatientBtn.disabled = true;

    } catch (error) {
        console.error("Delete failed:", error);
        alert("Unable to delete test");
    }
}
