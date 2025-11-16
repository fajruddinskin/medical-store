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

// PATIENT CREATION FUNCTION
async function createPatient() {
    const name = document.getElementById('patientName').value.trim();
    const phone = document.getElementById('patientPhone').value.trim();
    const email = document.getElementById('patientEmail').value.trim();
    const patientAge = document.getElementById('age').value.trim();
    const patientHistory = document.getElementById('medicalHistory').value.trim();
    const gender = document.getElementById('gender').value.trim();
    const bloodGroup = document.getElementById('bloodGroup').value.trim();
    const Doctor = document.getElementById('doctorName').value.trim();
    const Reffer = document.getElementById('refferBy').value.trim();

    const messageDiv = document.getElementById('patientMessage');

    // Basic validation
    if (!name || !phone) {
        showPatientMessage('Please fill in all required fields (Name and Phone).', 'danger');
        return;
    }

    const patientData = {
        name: name,
        gender: gender,
        phoneNumber: phone,
        email: email || null,
        age: patientAge,
        bloodGroup:bloodGroup,
        medicalHistory: patientHistory,
        doctor :Doctor,
        reffered_By :Reffer,
    };

    try {
        showPatientMessage('Creating patient...', 'info');

        const response = await fetch('/api/patients', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(patientData)
        });

        if (response.ok) {
            const createdPatient = await response.json();
            showPatientMessage(`✅ Patient "${createdPatient.name}" created successfully!`, 'success');
            clearPatientForm();

            // Collapse the form after success
            const collapseEl = document.getElementById('patientFormCollapse');
            const bsCollapse = bootstrap.Collapse.getOrCreateInstance(collapseEl);
            bsCollapse.hide();

            // Optional: refresh after short delay
            setTimeout(() => location.reload(), 1500);
        } else {
            const errorData = await response.json().catch(() => ({}));
            showPatientMessage(`❌ Error: ${errorData.message || 'Failed to create patient'}`, 'danger');
        }
    } catch (error) {
        console.error('Error creating patient:', error);
        showPatientMessage('⚠️ Network error: Could not create patient. Please try again.', 'danger');
    }
}

// Clear Form
function clearPatientForm() {
    document.getElementById('patientForm').reset();
    document.getElementById('patientMessage').innerHTML = '';
}

// Show Patient Message
function showPatientMessage(message, type) {
    const messageDiv = document.getElementById('patientMessage');
    const alertClass =
        type === 'success' ? 'alert-success' :
        type === 'danger' ? 'alert-danger' :
        'alert-info';

    messageDiv.innerHTML = `
        <div class="alert ${alertClass} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
}
