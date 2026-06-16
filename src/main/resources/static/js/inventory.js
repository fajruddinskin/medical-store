let returnItems = [];
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

function loadSuppliers() {

    fetch('/api/suppliers')
        .then(response => response.json())
        .then(data => {

            let html = `
                <div class="card mt-3">
                    <div class="card-header">
                        <h5>Supplier List</h5>
                    </div>
                        
                    <div class="card-body">

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Supplier Name</th>
                                    <th>Contact Person</th>
                                    <th>Phone</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
            `;

            data.forEach((supplier, index) => {

                html += `
                    <tr>
                        <td>${index + 1}</td>
                        <td>${supplier.supplierName}</td>
                        <td>${supplier.contactPerson}</td>
                        <td>${supplier.phone}</td>
                         <td>
        <span class="badge ${
                    supplier.status === 'ACTIVE'
                        ? 'bg-success'
                        : 'bg-danger'
                }">
            ${supplier.status}
        </span>
    </td>
    <td>
        <button class="btn btn-sm btn-primary"
                onclick="editSupplier(${supplier.id})">
            Edit
        </button>

        <button class="btn btn-sm btn-warning"
                onclick="toggleSupplierStatus(${supplier.id})">
            ${supplier.status === 'ACTIVE'
                    ? 'Deactivate'
                    : 'Activate'}
        </button>
    </td>
                    </tr>
                `;
            });

            html += `
                            </tbody>
                        </table>

                    </div>
                </div>
            `;

            document.getElementById("contentArea").innerHTML = html;
        });
}
function toggleSupplierStatus(id) {

    fetch(`/api/suppliers/${id}/status`, {
        method: 'PUT'
    })
        .then(response => response.json())
        .then(data => {

            alert('Status updated successfully');

            loadSuppliers();
        })
        .catch(error => {
            console.error(error);
        });
}
function showSuggestions(query) {
    const box = document.getElementById("suggestionBox");

    if (!box) return;

    if (!query || query.trim().length === 0) {
        box.innerHTML = "";
        box.style.display = "none";
        return;
    }

    fetch(`/api/suppliers/search?query=${encodeURIComponent(query)}`)
        .then(response => {
            console.log("HTTP Status:", response.status);  // DEBUG 1
            return response.json();
        })
        .then(data => {
            console.log("API Response Data:", data);       // DEBUG 2
            renderSuggestions(data);
        })
        .catch(err => {
            console.error("Search error:", err);           // ERROR LOG
        });
}
/*function renderSuggestions(list) {
    const box = document.getElementById("suggestionBox");

    if (!box) return;

    if (!list || list.length === 0) {
        box.innerHTML = `<div class="list-group-item">No results found</div>`;
        box.style.display = "block";
        return;
    }

    box.innerHTML = list.map(item => `
        <div class="list-group-item"
             onclick="selectSupplier('${item.supplierName}')">
            ${item.supplierName}
        </div>
    `).join("");

    box.style.display = "block";
}*/
function renderSuggestions(list) {
    const box = document.getElementById("suggestionBox");

    if (!box) return;

    if (!list || list.length === 0) {
        box.innerHTML = `<div class="list-group-item">No results found</div>`;
        box.style.display = "block";
        return;
    }

    box.innerHTML = list.map(item => `
        <div class="list-group-item"
             onclick='selectSupplier(${JSON.stringify(item)})'>
            ${item.supplierName}
        </div>
    `).join("");

    box.style.display = "block";
}
function renderTable(item) {

    let table = document.getElementById("supplierTable");

    if (!table) {
        table = document.createElement("table");
        table.id = "supplierTable";
        table.className = "table table-bordered mt-3";

        table.innerHTML = `
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Supplier Name</th>
                    <th>Contact Person</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody id="supplierTableBody"></tbody>
        `;

        document.body.appendChild(table);
    }

    const tbody = document.getElementById("supplierTableBody");

    tbody.innerHTML = `
        <tr>
            <td>${item.id}</td>
            <td>${item.supplierName}</td>
            <td>${item.contactPerson}</td>
            <td>${item.phone}</td>
            <td>${item.email}</td>
            <td>${item.address}</td>
            <td>${item.status}</td>
        </tr>
    `;
}
function selectSupplier(item) {

    // fill input box
    document.getElementById("supplierSearch").value = item.supplierName;

    // hide suggestion box
    document.getElementById("suggestionBox").innerHTML = "";
    document.getElementById("suggestionBox").style.display = "none";

    // show full row in table
    renderTable(item);
}
function loadSupplierReturn() {

    document.getElementById("contentArea").innerHTML = `

        <div class="card mt-4 shadow">

            <div class="card-header bg-warning">
                <h5 class="mb-0">
                    Return To Supplier
                </h5>
            </div>

            <div class="card-body">

                <div class="row">

                    <div class="col-md-4">
                        <label class="form-label">
                            Supplier
                        </label>

                        <select id="supplierId"
                                class="form-select">

                            <option value="">
                                Select Supplier
                            </option>

                        </select>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">
                            Return Date
                        </label>

                        <input type="date"
                               id="returnDate"
                               class="form-control">
                    </div>

                    <div class="col-md-4">
                        <label class="form-label">
                            Reason
                        </label>

                        <select id="returnReason"
                                class="form-select">

                            <option value="EXPIRED">
                                Expired
                            </option>

                            <option value="DAMAGED">
                                Damaged
                            </option>

                            <option value="WRONG_SUPPLY">
                                Wrong Supply
                            </option>

                            <option value="EXCESS_STOCK">
                                Excess Stock
                            </option>

                        </select>
                    </div>

                </div>

                <div class="row mt-3">

                    <div class="col-md-12">

                        <label class="form-label">
                            Remarks
                        </label>

                        <textarea id="remarks"
                                  class="form-control"
                                  rows="3"
                                  placeholder="Enter remarks">
                        </textarea>
                    <hr>

<h5>Add Medicine</h5>

<div class="row">

    <div class="col-md-4">
        <label class="form-label">Medicine</label>

        <input type="hidden"
           id="medicineId">

    <input type="text"
           id="medicineSearch"
           class="form-control"
           placeholder="Search Medicine"
           onkeyup="searchMedicine()">

    <div id="medicineSuggestions"
         class="list-group position-absolute w-100"
         style="z-index:1000;">
    </div>
    </div>

    <div class="col-md-2">
        <label class="form-label">Stock</label>

        <input type="text"
               id="currentStock"
               class="form-control"
               readonly>
    </div>

    <div class="col-md-2">
        <label class="form-label">Price</label>

        <input type="text"
               id="purchasePrice"
               class="form-control"
               readonly>
    </div>

    <div class="col-md-2">
        <label class="form-label">Quantity</label>

        <input type="number"
               id="returnQty"
               class="form-control"
               min="1">
    </div>

    <div class="col-md-2 d-flex align-items-end">

        <button class="btn btn-danger w-100"
                onclick="addReturnItem()">

            Add Item

        </button>

    </div>

</div>
<hr>

<div class="table-responsive">

    <table class="table table-bordered">

        <thead class="table-light">

        <tr>
            <th>Medicine</th>
            <th>Stock</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>

        </thead>

        <tbody id="returnItemsTable">

        </tbody>

    </table>

</div>

<div class="text-end">

    <h4>
        Total :
        ₹ <span id="totalAmount">0.00</span>
    </h4>

</div>
    <div class="mt-3 text-end">

    <button type="button"
            class="btn btn-success"
            onclick="saveReturn()">
        <i class="fas fa-save"></i> Save Return
    </button>

    <button type="button"
            class="btn btn-secondary"
            onclick="clearReturnItems()">
        <i class="fas fa-trash"></i> Clear
    </button>

  </div>      
  </div>

                </div>

            </div>

        </div>

    `;

    document.getElementById("returnDate").value =
        new Date().toISOString().split("T")[0];

    loadSupplierDropdown();
 //   loadSuppliers();
}
function loadSupplierDropdown() {

    fetch('/api/suppliers')
        .then(response => response.json())
        .then(data => {

            const supplierSelect =
                document.getElementById("supplierId");

            supplierSelect.innerHTML =
                '<option value="">Select Supplier</option>';

            data.forEach(supplier => {

                if (supplier.status === 'ACTIVE') {

                    supplierSelect.innerHTML += `
                        <option value="${supplier.id}">
                            ${supplier.supplierName}
                        </option>
                    `;
                }
            });
        })
        .catch(error => {
            console.error(
                "Error loading suppliers:",
                error
            );
        });
}
function searchMedicine() {

    const searchTerm =
        document.getElementById("medicineSearch").value;

    if (searchTerm.length < 2) {
        document.getElementById(
            "medicineSuggestions"
        ).innerHTML = "";
        return;
    }

    fetch(`/api/medicines/search?searchTerm=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(data => renderMedicineSuggestions(data))
        .catch(error => {
            console.error("Medicine Search Error:", error);
        });
}
function renderMedicineSuggestions(medicines) {

    const suggestionBox =
        document.getElementById(
            "medicineSuggestions"
        );

    suggestionBox.innerHTML = "";

    medicines.forEach(medicine => {

        suggestionBox.innerHTML += `

            <a href="#"
               class="list-group-item list-group-item-action"
               onclick="selectMedicine(
                   ${medicine.id},
                   '${medicine.name}',
                   ${medicine.quantity},
                   ${medicine.price}
               )">

                ${medicine.name}
                (Stock: ${medicine.quantity})

            </a>

        `;
    });
}
function selectMedicine(
    id,
    name,
    quantity,
    price
) {

    document.getElementById("medicineId").value = id;

    document.getElementById("medicineSearch").value = name;

    document.getElementById("currentStock").value = quantity;

    document.getElementById("purchasePrice").value = price;

    document.getElementById("medicineSuggestions").innerHTML = "";

    document.getElementById("returnQty").focus();
}
//let returnItems = [];

function addReturnItem() {

    const medicineId =
        document.getElementById("medicineId").value;

    const medicineName =
        document.getElementById("medicineSearch").value;

    const stock =
        parseInt(
            document.getElementById("currentStock").value
        );

    const price =
        parseFloat(
            document.getElementById("purchasePrice").value
        );

    const quantity =
        parseInt(
            document.getElementById("returnQty").value
        );

    if (!medicineId) {
        alert("Please select a medicine");
        return;
    }

    if (!quantity || quantity <= 0) {
        alert("Please enter a valid quantity");
        return;
    }

    if (quantity > stock) {
        alert("Return quantity cannot exceed stock");
        return;
    }

    const existingItem = returnItems.find(
        item => item.medicineId == medicineId
    );

    if (existingItem) {

        existingItem.quantity += quantity;

        if (existingItem.quantity > stock) {
            alert("Total quantity exceeds available stock");
            existingItem.quantity -= quantity;
            return;
        }

        existingItem.amount =
            existingItem.quantity * existingItem.price;

    } else {

        returnItems.push({
            medicineId: medicineId,
            medicineName: medicineName,
            stock: stock,
            purchasePrice: price,
            quantity: quantity,
            amount: quantity * price
        });
    }

    renderReturnItems();

    clearMedicineInputs();
}
function renderReturnItems() {
  //  console.log(returnItems);
   const tbody =
        document.getElementById("returnItemsTable");

    tbody.innerHTML = "";

    let total = 0;

    returnItems.forEach((item, index) => {

        total += item.amount;

        tbody.innerHTML += `
            <tr>
                <td>${item.medicineName}</td>
                <td>${item.stock}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>${item.amount}</td>
                <td>
    <button
        class="btn btn-danger btn-sm"
        onclick="removeReturnItem(${index})">

        Remove

    </button>
</td>
            </tr>
        `;
    });

    document.getElementById("totalAmount")
        .innerText = total.toFixed(2);
}
function removeReturnItem(index) {

    returnItems.splice(index, 1);

    renderReturnItems();

    if (returnItems.length === 0) {

        document.getElementById("totalAmount")
            .innerText = "0.00";
    }
}
function clearMedicineInputs() {

    document.getElementById("medicineId").value = "";

    document.getElementById("medicineSearch").value = "";

    document.getElementById("currentStock").value = "";

    document.getElementById("purchasePrice").value = "";

    document.getElementById("returnQty").value = "";

    const suggestionBox =
        document.getElementById("medicineSuggestions");

    if (suggestionBox) {
        suggestionBox.innerHTML = "";
    }

    document.getElementById("medicineSearch").focus();
}
function saveReturn() {

    const supplierId =
        document.getElementById("supplierId").value;

    const returnDate =
        document.getElementById("returnDate").value;

    const reason =
        document.getElementById("returnReason").value;

    const remarks =
        document.getElementById("remarks").value;

    if (!supplierId) {
        alert("Please select a supplier");
        return;
    }

    if (!returnDate) {
        alert("Please select return date");
        return;
    }

    if (returnItems.length === 0) {
        alert("Please add at least one medicine");
        return;
    }

    const returnData = {
        supplierId: Number(supplierId),
        returnDate: returnDate,
        reason: reason,
        remarks: remarks,
        items: returnItems
    };

    console.log("Saving Return:");
    console.log(returnData);

    fetch("/api/returns/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(returnData)
    })
        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to save return");
            }

            return response.json();
        })
        .then(data => {

            alert("Return saved successfully");

            console.log("Saved:", data);

            returnItems = [];

            renderReturnItems();

            document.getElementById("supplierId").value = "";
            document.getElementById("remarks").value = "";

            document.getElementById("totalAmount").innerText = "0.00";
        })
        .catch(error => {

            console.error("Save Error:", error);

            alert("Error while saving return");
        });
}

function clearReturnItems() {

    if (!confirm("Clear all items?")) {
        return;
    }

    returnItems = [];
    renderReturnItems();
}
function loadReturnHistory() {

    fetch("/api/returns")
        .then(response => response.json())
        .then(data => {

            let html = `
                <div class="card">
                    <div class="card-header">
                        <h5>Supplier Return History</h5>
                    </div>

                    <div class="card-body">

                        <table class="table table-bordered">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Date</th>
                                    <th>Supplier</th>
                                    <th>Total</th>
                                </tr>
                            </thead>

                            <tbody>
            `;

            data.forEach(item => {

                html += `
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.returnDate}</td>
                        <td>${item.supplier.supplierName}</td>
                        <td>${item.totalAmount}</td>
                    </tr>
                `;
            });

            html += `
                            </tbody>
                        </table>
                    </div>
                </div>
            `;

            document.getElementById("contentArea")
                .innerHTML = html;
        });
}