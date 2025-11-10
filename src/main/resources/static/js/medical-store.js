document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("labTestForm");
    const messageDiv = document.getElementById("labTestMessage");
    const tableBody = document.getElementById("labTestsTableBody");

    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const testData = {
            name: document.getElementById("testName").value.trim(),
            description: document.getElementById("testDescription").value.trim(),
            price: parseFloat(document.getElementById("testPrice").value),
            referrerFee: parseFloat(document.getElementById("testReferrerFee").value) || 0
        };

        messageDiv.innerHTML = `<div class="alert alert-info">Saving...</div>`;

        try {
            const response = await fetch("/api/lab-tests", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(testData)
            });

            if (response.ok) {
                const newTest = await response.json();
                messageDiv.innerHTML = `<div class="alert alert-success">✅ Lab Test "${newTest.name}" added successfully!</div>`;
                form.reset();

                // Add new row
                const newRow = `
                    <tr>
                        <td>${newTest.id}</td>
                        <td>${newTest.name}</td>
                        <td>${newTest.description || "-"}</td>
                        <td>$${newTest.price.toFixed(2)}</td>
                        <td>$${(newTest.referrerFee || 0).toFixed(2)}</td>
                    </tr>`;
                if (tableBody.querySelector("td.text-muted")) tableBody.innerHTML = "";
                tableBody.insertAdjacentHTML("afterbegin", newRow);

                // Collapse form
                const collapse = bootstrap.Collapse.getOrCreateInstance(document.getElementById("labTestFormCollapse"));
                collapse.hide();
            } else {
                const errText = await response.text();
                messageDiv.innerHTML = `<div class="alert alert-danger">❌ Failed: ${errText}</div>`;
            }
        } catch (err) {
            console.error(err);
            messageDiv.innerHTML = `<div class="alert alert-danger">⚠️ Server error. Please try again.</div>`;
        }
    });
});

$(document).ready(function() {
    // Trigger search when button is clicked
    $('#testSearchBtn').on('click', function(e) {
        e.preventDefault();
        performSearch();
    });

    // Optional: Trigger search on Enter key press
    $('#testSearchInput').on('keypress', function(e) {
        if (e.which === 13) {
            performSearch();
        }
    });

    function performSearch() {
        const searchTerm = $('#testSearchInput').val().trim();

        if (!searchTerm) {
            alert("Please enter a search term.");
            return;
        }

        $.ajax({
            url: '/api/lab-tests/search', // your Spring Boot endpoint
            method: 'GET',
            data: { searchTerm: searchTerm },
            success: function(response) {
                renderResults(response);
            },
            error: function(xhr) {
                console.error("Error fetching lab tests:", xhr.responseText);
                $('#labTestsTableBody').html(
                    `<tr><td colspan="5" class="text-center text-danger">Failed to fetch lab tests. Please try again.</td></tr>`
                );
            }
        });
    }

    function renderResults(data) {
        const tbody = $('#labTestsTableBody');
        tbody.empty(); // Clear previous results

        if (!data || data.length === 0) {
            tbody.html('<tr><td colspan="5" class="text-center text-muted">No lab tests found</td></tr>');
            return;
        }

        data.forEach(test => {
            tbody.append(`
                <tr>
                    <td>${test.id || '-'}</td>
                    <td>${test.name || '-'}</td>
                    <td>${test.description || '-'}</td>
                    <td>${test.price != null ? test.price : '-'}</td>
                    <td>${test.referrerFee != null ? test.referrerFee : '-'}</td>
                </tr>
            `);
        });
    }
});