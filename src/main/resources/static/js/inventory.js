function hideInventoryTable() {

    document.getElementById("inventoryTableSection")
        .style.display = "none";
}
function renderLowStock(data){

    document.getElementById("tableTitle").innerText =
        "Low Stock Medicines";

    document.getElementById("tableHead").innerHTML = `
        <tr>
            <th>Name</th>
            <th>Batch</th>
            <th>Quantity</th>
        </tr>
    `;

    let rows = "";

    data.forEach(medicine => {

        rows += `
            <tr>
                <td>${medicine.name}</td>
                <td>${medicine.batchNumber}</td>
                <td>${medicine.quantity}</td>
            </tr>
        `;
    });

    document.getElementById("tableBody").innerHTML = rows;
}
function loadInventoryData(type) {

    document.getElementById("inventoryTableSection")
        .style.display = "block";

    fetch('/inventory/' + type)
        .then(response => response.json())
        .then(data => {

            switch(type) {

                case 'low-stock':
                    renderLowStock(data);
                    break;

                case 'expiry':
                    renderExpiry(data);
                    break;

                case 'purchase':
                    renderPurchase(data);
                    break;
                case 'adjustmentHistory':
                    renderAdjustment(data);
                    break;
            }

        });
}
function renderExpiry(data) {

    document.getElementById("tableTitle").innerText =
        "Expiring Medicines";

    document.getElementById("tableHead").innerHTML = `
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Batch No</th>
            <th>Expiry Date</th>
            <th>Quantity</th>
        </tr>
    `;

    let rows = '';

    if(data.length === 0) {

        rows = `
            <tr>
                <td colspan="5" class="text-center text-success">
                    No Expiring Medicines Found
                </td>
            </tr>
        `;

    } else {

        data.forEach(medicine => {

            rows += `
                <tr>
                    <td>${medicine.id}</td>
                    <td>${medicine.name}</td>
                    <td>${medicine.batchNumber}</td>
                    <td>${medicine.expiryDate || ''}</td>
                    <td>${medicine.quantity}</td>
                </tr>
            `;
        });

    }

    document.getElementById("tableBody").innerHTML = rows;
}
function renderPurchase(data) {

    console.log("DATA:", data);

    // 1. Set title
    document.getElementById("tableTitle").innerText = "Purchase History";

    // 2. Set table header
    document.getElementById("tableHead").innerHTML = `
        <tr>
            <th>#</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total</th>
            <th>Purchase Date</th>
        </tr>
    `;

    // 3. Build rows
    let html = "";

    if (!data || data.length === 0) {
        html = `<tr>
                    <td colspan="6" class="text-center">No Data Found</td>
                </tr>`;
    } else {

        data.forEach((item, index) => {

            html += `
                <tr>
                    <td>${index + 1}</td>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price}</td>
                    <td>${item.quantity * item.price}</td>
                     <td>${item.purchaseDate}</td>
                </tr>
            `;
        });
    }

    // 4. Set body
    document.getElementById("tableBody").innerHTML = html;
}

function showAdjustmentForm() {

    const modal = new bootstrap.Modal(
        document.getElementById('adjustmentModal')
    );

    modal.show();
}
function saveAdjustment() {

    const adjustment = {

        medicineId: document.getElementById("medicineId").value,
        adjustmentType: document.getElementById("adjustmentType").value,
        quantity: document.getElementById("quantity").value,
        reason: document.getElementById("reason").value,
        adjustedBy: "Admin"
    };

    fetch('/inventory/adjustment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(adjustment)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to save adjustment");
            }
            return response.json();
        })
        .then(data => {

            alert("Inventory Adjustment Saved Successfully");

            document.getElementById("medicineId").value = "";
            document.getElementById("adjustmentType").value = "DAMAGE";
            document.getElementById("quantity").value = "";
            document.getElementById("reason").value = "";

            const modal =
                bootstrap.Modal.getInstance(
                    document.getElementById('adjustmentModal')
                );

            if(modal){
                modal.hide();
            }

            loadInventoryData('history');
        })
        .catch(error => {
            console.error(error);
            alert("Error saving adjustment");
        });
}
function renderAdjustment(data) {

    document.getElementById("tableTitle").innerText =
        "Inventory Adjustments";

    document.getElementById("tableHead").innerHTML = `
    <tr>
        <th>#</th>
        <th>Medicine</th>
        <th>Type</th>
        <th>Quantity</th>
        <th>Reason</th>
        <th>Stock Before</th>
        <th>Stock After</th>
        <th>Date</th>
        <th>Adjusted By</th>
    </tr>
    `;

    if (!data || data.length === 0) {
        document.getElementById("tableBody").innerHTML = `
            <tr>
                <td colspan="9" class="text-center text-muted">
                    No adjustment records found
                </td>
            </tr>
        `;
        return;
    }

    let rows = "";

    data.forEach((item, index) => {

        rows += `
            <tr>
                <td>${index + 1}</td>
                <td>${item.medicineName ?? '-'}</td>
                <td>
                    <span class="badge bg-warning text-dark">
                        ${item.adjustmentType ?? '-'}
                    </span>
                </td>
                <td>${item.quantity ?? 0}</td>
                <td>${item.reason ?? '-'}</td>
                <td>${item.stockBefore ?? '-'}</td>
                <td>${item.stockAfter ?? '-'}</td>
                <td>${item.adjustmentDate ?? '-'}</td>
                <td>${item.adjustedBy ?? '-'}</td>
            </tr>
        `;
    });

    document.getElementById("tableBody").innerHTML = rows;
}
function loadStockLedger() {

    document.getElementById("inventoryTableSection")
        .style.display = "block";

    fetch('/inventory/stock-ledger')
        .then(response => response.json())
        .then(data => {

            let html = `
                <div class="card shadow">
                    <div class="card-header">
                        <h5>Stock In / Stock Out Ledger</h5>
                    </div>

                    <div class="card-body table-responsive">

                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Medicine</th>
                                    <th>Type</th>
                                    <th>Qty</th>
                                    <th>Before</th>
                                    <th>After</th>
                                    <th>Reason</th>
                                    <th>Adjusted By</th>
                                </tr>
                            </thead>
                            <tbody>
            `;

            data.forEach(item => {

                html += `
                    <tr>
                        <td>${item.date ?? '-'}</td>
                        <td>${item.medicineName ?? '-'}</td>
                        <td>${item.transactionType ?? '-'}</td>
                        <td>${item.quantity ?? 0}</td>
                        <td>${item.stockBefore ?? '-'}</td>
                        <td>${item.stockAfter ?? '-'}</td>
                        <td>${item.reason ?? '-'}</td>
                        <td>${item.adjustedBy ?? '-'}</td>
                    </tr>
                `;
            });

            html += `
                            </tbody>
                        </table>
                    </div>
                </div>
            `;

            document.getElementById("inventoryTableSection")
                .innerHTML = html;
        })
        .catch(error => {
            console.error("Error loading stock ledger:", error);
        });
}
function showSupplierForm() {

    let content = `
        <div class="card mt-3">
            <div class="card-header d-flex justify-content-between">
                <h5>Add Supplier</h5>

                <button class="btn btn-danger btn-sm"
                        onclick="closeSupplierForm()">
                    X
                </button>
            </div>

            <div class="card-body">

                <div class="mb-3">
                    <label>Supplier Name</label>
                    <input type="text"
                           id="supplierName"
                           class="form-control">
                </div>

                <div class="mb-3">
                    <label>Contact Person</label>
                    <input type="text"
                           id="contactPerson"
                           class="form-control">
                </div>

                <div class="mb-3">
                    <label>Phone</label>
                    <input type="text"
                           id="phone"
                           class="form-control">
                </div>
                <div class="mb-3">
    <label>Email</label>
    <input type="email"
           id="email"
           class="form-control">
</div>

<div class="mb-3">
    <label>Address</label>
    <textarea id="address"
              class="form-control"></textarea>
</div>

<div class="mb-3">
    <label>Status</label>
    <select id="status"
            class="form-control">
        <option value="ACTIVE">ACTIVE</option>
        <option value="INACTIVE">INACTIVE</option>
    </select>
</div>
                <button class="btn btn-primary"
                        onclick="saveSupplier()">
                    Save Supplier
                </button>

            </div>
        </div>
    `;

    document.getElementById("contentArea").innerHTML = content;
}

function closeSupplierForm() {
    document.getElementById("contentArea").innerHTML = "";
}
function saveSupplier() {

    const supplier = {
        supplierName: document.getElementById("supplierName").value.trim(),
        contactPerson: document.getElementById("contactPerson").value.trim(),
        phone: document.getElementById("phone").value.trim(),
        email: document.getElementById("email").value.trim(),
        address: document.getElementById("address").value.trim(),
        status: document.getElementById("status").value
    };

    fetch('/api/suppliers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(supplier)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to save supplier');
            }
            return response.json();
        })
        .then(data => {

            alert('Supplier saved successfully');

            // Clear form
            document.getElementById("supplierName").value = "";
            document.getElementById("contactPerson").value = "";
            document.getElementById("phone").value = "";
            document.getElementById("email").value = "";
            document.getElementById("address").value = "";
            document.getElementById("status").value = "ACTIVE";

            console.log("Saved Supplier:", data);

        })
        .catch(error => {
            console.error(error);
            alert('Error saving supplier');
        });
}