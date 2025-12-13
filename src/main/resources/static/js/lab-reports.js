    // =========================
    // SAVE REPORT
    // =========================
    function saveReport() {
        const content = tinymce.get("editor").getContent();
        const testId = $("#selectedTestId").val();
        const invoiceNo = $("#invoiceNo").val();
        const status = $("#statusDropdown").val();
    
        if (!testId) return alert("Please select a test first!");
    
        const payload = { testId, invoiceNo, status, content };
    
        fetch('/api/save-report', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        })
            .then(res => res.json())
            .then(data => alert(data.message))
            .catch(err => console.error(err));
    }
    
    // =========================
    // ENTER KEY SEARCH
    // =========================
    document.getElementById("searchBox").addEventListener("keypress", e => {
        if (e.key === "Enter") {
            e.preventDefault();
            applyFilter();
        }
    });
    
    // =========================
    // APPLY FILTER
    // =========================
    function applyFilter() {
        const s = $("#startDate").val();
        const e = $("#endDate").val();
        const w = $("#weeksFilter").val();
        const id = $("#searchBox").val();
    
        let url = "/api/filter";
        const params = [];
    
        if (id) params.push(`id=${id}`);
        if (s) params.push(`startDate=${s}`);
        if (e) params.push(`endDate=${e}`);
        if (w) params.push(`weeks=${w}`);
    
        if (params.length > 0) url += "?" + params.join("&");
    
        fetch(url)
            .then(res => res.json())
            .then(data => displayResult(data))
            .catch(console.error);
    }
    
    // =========================
    // DISPLAY TABLE
    // =========================
    let currentContainerData = null;
    
    function displayResult(data) {
    
        if (!Array.isArray(data) || data.length === 0) {
            alert("No records found!");
            return;
        }
    
        const container = data[0]; 
        currentContainerData = container;
    
        const body = document.getElementById("labTestsTableBody");
        body.innerHTML = "";
    
        container.labTests.forEach(test => {
            body.insertAdjacentHTML("beforeend", `
                <tr data-id="${test.id}">
                    <td>${test.id}</td>
                    <td>${test.name}</td>
                    <td>${test.reportStatus}</td>
                    <td>${container.id}</td>
                </tr>
            `);
        });
    
        document.getElementById("labTestsTableContainer").style.display = "block";
    }
    
    // =========================
    // CLICK ROW TO LOAD REPORT
    // =========================
    $(document).on("click", "#labTestsTableBody tr", function () {
        const testId = $(this).data("id");
    
        $("#labTestsTableBody tr").removeClass("selected-row");
        $(this).addClass("selected-row");
    
        const selectedTest = currentContainerData.labTests.find(t => t.id == testId);
        if (!selectedTest) return alert("Test not found!");
    
        tinymce.get("editor").setContent(selectedTest.medicalReport?.content || "");
    
        $("#selectedTestId").val(testId);
        $("#invoiceNo").val(currentContainerData.id);
        $("#reportStatus").val(selectedTest.reportStatus);
        $("#statusDropdown").val(selectedTest.reportStatus);

    });
    
    // =========================
    // CLEAR FILTERS
    // =========================
    function clearFilters() {
        $("#startDate, #endDate, #weeksFilter, #searchBox").val("");
        $("#labTestsTableBody").html("");
        $("#labTestsTableContainer").hide();
    }
    
    // =========================
    // UPDATE BUTTON
    // =========================
    function updateContainer() {
        applyFilter();
    }

    // =========================
    // PRINT REPORT
    // =========================
    function printReport() {

        const content = tinymce.get("editor").getContent();
        const testId = $("#selectedTestId").val();
        const invoiceNo = $("#invoiceNo").val();

        if (!testId) {
            alert("Please select a test before printing!");
            return;
        }

        const printWindow = window.open("", "", "width=900,height=650");

        printWindow.document.write(`
            <html>
            <head>
                <title>Lab Report</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    body {
                        padding: 20px;
                        font-family: Arial, sans-serif;
                    }
                    .header {
                        text-align: center;
                        margin-bottom: 20px;
                    }
                    .footer {
                        margin-top: 40px;
                        text-align: right;
                        font-size: 14px;
                    }
                </style>
            </head>
            <body>
                <div>
                    ${content}
                </div>

                <div class="footer">
                    <p>Generated on: ${new Date().toLocaleString()}</p>
                </div>

            </body>
            </html>
        `);

        printWindow.document.close();
        printWindow.focus();

        setTimeout(() => {
            printWindow.print();
            printWindow.close();
        }, 500);
    }
