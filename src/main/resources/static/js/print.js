// =======================================================
// ==========================
// Utility: Convert number to words (simplified placeholder)
// ==========================
function numberToWords(num) {
    return `Rupees ${num.toFixed(2)}`;
}

// ==========================
// Generate printable bill HTML dynamically
// ==========================
function generateBillHTML(data) {
    const patient = data.patient || {};

    const styleContent = `
        body {
            font-family: Arial, sans-serif;
            font-size: 14px;
            margin: 10px;
            color: #000;
        }
        .bill-container {
            width: 100%;
        }
       .page-border {
           border: 2px solid #000;
           padding: 15px;
           margin: 5px;
           height: 50vh;        /* Half page */
           max-height: 50vh;    /* Prevent expanding */
           box-sizing: border-box;
       }
        .line { border-top: 1px solid #000; margin: 5px 0; }
        .center { text-align: center; }
        .bold { font-weight: bold; }
        .details-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
            line-height: 1.4;
        }

        .row-flex {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            width: 100%;
            margin-top: 10px;
        }

        .terms-left {
            width: 50%;
            text-align: left;
            font-size: 12px;
        }

        .totals-right {
            width: 50%;
            text-align: right;
            line-height: 1.5;
            font-size: 14px;
        }


        .details-row div {
            width: 48%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 13px;
            margin: 5px 0;
        }
        table th, table td {
            border: 1px solid #000;
            padding: 3px 5px;
            text-align: left;
        }
        .single-row { margin: 5px 0; }
        .single-row span { display: block; margin-bottom: 2px; }
        .footer-section { margin-top: 10px; line-height: 1.4; font-size: 12px; }
        .sign { text-align: center; margin-top: 10px; }
    `;

    // Lab details (left) and patient info (right)
    const headerHTML = `
        <div class="details-row">
            <div>
                LITTLE HEART MEDICAL<br>
                MURARAI HOSPITAL ROAD, BIRBHUM W.B-731219<br>
                PHONE: 9732491505<br>
                Email: Ilthislam@gmail.com
            </div>
            <div>
                <b>Patient Name:</b> ${patient.name || 'N/A'}<br>
                <b>Patient Address:</b> ${patient.address || 'N/A'}<br>
                <b>Doctor Name:</b> ${patient.medicalHistory || 'N/A'}<br>
                <b>Doctor Reg No:</b> ${patient.reffered_By || 'N/A'}
            </div>
        </div>
        <div class="line"></div>
    `;

    const invoiceHTML = `
        <div class="details-row">
            <div>
                <b>GSTIN:</b> 19<br>
                <b>D.L.No.:</b> WB/BBM/NBO/R@ BIO/R/559081
            </div>
            <div class="center bold">CASH MEMO</div>
            <div style="text-align:right;">
                <b>Invoice No.:</b> ${data.id || 'N/A'}<br>
                <b>Date:</b> ${data.deliveryDate || new Date().toLocaleDateString()}
            </div>
        </div>
        <div class="line"></div>
    `;

    // Lab tests table rows
    const labRows = (data.labTests || []).map((test, idx) => `
        <tr>
            <td>${idx + 1}</td>
            <td>${test.name}</td>
            <td>${((test.price || 0) * (test.qty || 1)).toFixed(2)}</td>
        </tr>
    `).join('');

   const totalsHTML = `
       <div class="row-flex">

           <div class="terms-left">
               <b>Terms & Conditions:</b><br>
               • Goods once sold will not be taken back or exchanged.<br>
               • Bills not paid due date will attract 24% interest.<br>
               • All disputes subject to jurisdiction only.<br>
               • Prescribed sales tax declaration will be given.<br>
           </div>

           <div class="totals-right">
               <div>GST: ${data.total || 0} × 0 = 0</div>
               <div>SUB TOTAL: ${(data.subTotal || 0).toFixed(2)}</div>
               <div>DISCOUNT: ${(data.discount || 0).toFixed(2)}</div>
               <div>GRAND TOTAL: ${(data.total || 0).toFixed(2)}</div>
           </div>
       </div>
      <div class="line"></div>
   `;



    return `
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Medical Bill</title>
            <style>${styleContent}</style>
        </head>
        <body>
            <div class="bill-container page-border">
                ${headerHTML}
                ${invoiceHTML}
                <table>
                    <tr>
                        <th>Test ID</th><th>Name</th><th>Price</th>
                    </tr>
                    ${labRows}
                </table>
                ${totalsHTML}
            </div>
            <script>window.onload = function() { window.print(); }</script>
        </body>
        </html>
    `;
}


// ==========================
// Print button event handler
// ==========================
document.getElementById('printReportBtn')?.addEventListener('click', () => {
    const containerId = document.getElementById("containerId")?.value;
    if (!containerId) return console.warn('Container ID missing');

    const apiUrl = `/api/print/${containerId}`; // Replace with actual endpoint

    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            // Generate printable HTML from your data
            const billHTML = generateBillHTML(data);

            // Open in a new window for printing
            const printWindow = window.open('', 'myPrintWindow');
            printWindow.document.open();
            printWindow.document.write(billHTML);
            printWindow.document.close();
            // Print will automatically trigger because of your <script> in billHTML
        })
        .catch(err => console.error('Error fetching report:', err));
});
