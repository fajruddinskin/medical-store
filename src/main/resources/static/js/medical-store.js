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
