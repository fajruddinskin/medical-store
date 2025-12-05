function saveReport() {
    const content = tinymce.get("editor").getContent();

    const testId = $("#selectedTestId").val();
    const invoiceNo = $("#invoiceNo").val();
    const status = $("#reportStatus").val();

    if (!testId) {
        alert("Please select a test first!");
        return;
    }

    const payload = {
        testId: testId,
        invoiceNo: invoiceNo,
        status: status,
        content: content
    };

    fetch('/api/save-report', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    })
        .then(res => res.json())
        .then(data => alert(data.message))
        .catch(err => console.error(err));
}

document.getElementById('searchBox').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        e.preventDefault();
        searchContainer();
    }
});

function searchContainer() {
    const id = document.getElementById("searchBox").value;
    if (!id) {
        alert("Please enter an ID!");
        return;
    }

    fetch(`api/search/${id}`)
        .then(res => res.json())
        .then(data => displayResult(data))
        .catch(err => console.error(err));
}

function displayResult(data) {
    currentContainerData = data;   // ⬅️ Save full data globally

    document.getElementById("labTestsTableContainer").style.display = "block";
    const body = document.getElementById("labTestsTableBody");
    $("#containerId").val(data.id);
    body.innerHTML = "";

    data.labTests.forEach(test => {
        body.insertAdjacentHTML(
            "beforeend",
            `
            <tr data-id="${test.id}">
                <td>${test.id}</td>
                <td>${test.name}</td>
                <td>${test.reportStatus}</td>
                <td>${data.id}</td>
            </tr>
            `
        );
    });
}


// Listen for table row clicks
$(document).on("click", "#labTestsTableBody tr", function () {
    let testId = $(this).data("id");

    $("#labTestsTableBody tr").removeClass("selected-row");
    $(this).addClass("selected-row");

    if (!currentContainerData) return;

    const selectedTest = currentContainerData.labTests.find(t => t.id == testId);
    if (!selectedTest) {
        alert("Test not found!");
        return;
    }

    // Load editor content
    const reportContent = selectedTest.medicalReport?.content || "<p>No report available.</p>";
    tinymce.get("editor").setContent(reportContent);

    // Store values in hidden fields
    $("#selectedTestId").val(testId);
    $("#invoiceNo").val(currentContainerData.id);
    $("#reportStatus").val(selectedTest.reportStatus);
});


