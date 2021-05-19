
var customerId = $.url('#customerId');
var orderId = $.url('#orderId');


$(() => {
    $.getJSON(`/api/customers/${customerId}/orders/${orderId}`)
     .done((order) => {
        $('#titleOrderNumber').text(order.number);
        $('#buttonDone').attr('href', `/customers/${customerId}/orders/`);
    });

    $.getJSON(`/api/customers/${customerId}/orders/${orderId}/products`)
     .done((orderProducts) => orderProducts.forEach(addOrderProductRow)
    );

    $('#productAddName').autocomplete({
        source: searchProductByTerm,
        select: (ev, ui) => $('#productAddId').val(ui.item.id)
    });

    $('#buttonProductAdd').click(() => {
        var productId = $('#productAddId').val();
        var productName = $('#productAddName').val();
        var quantity = $('#productAddQuantity').val();
        $('#productAddId, #productAddName, #productAddQuantity').val(null);

        $.ajax({
            url: `/api/customers/${customerId}/orders/${orderId}/products`,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                product: { id: productId },
                quantity: quantity
            })
        }).done(addOrUpdateOrderProductRow);
    });
})

function addOrUpdateOrderProductRow(orderProduct) {
    var existingRow = $("#op_" + orderProduct.product.id);
    if (existingRow.length === 1) {
        existingRow.find('[name="spanQuantity"]')
            .hide().text(orderProduct.quantity).show('slow');
        existingRow.find('[name=spanValue]')
            .hide().text(orderProduct.value).show('slow');

    } else {
        addOrderProductRow(orderProduct);
    }
}

function addOrderProductRow(orderProduct) {
    var newRow = $(`
        <tr id="op_${orderProduct.product.id}">
            <th>${orderProduct.product.name}</th>
            <th><span name="spanQuantity">${orderProduct.quantity}</span></th>
            <th><span name="spanValue">${orderProduct.value}</span></th>
            <th><img src="${orderProduct.product.url}" width="15px height=15px"/></th>
            <th><button name="buttonProductRemove" class="btn btn-info">Remove</button></th>
        </tr>
    `);
    newRow.find('[name="buttonProductRemove"]').click(() => {
        $.ajax({
            url: `/api/customers/${customerId}/orders/${orderId}/products/${orderProduct.product.id}`,
            type: 'DELETE'
        }).done(() =>
            newRow.hide(400, () => newRow.remove())
        );
    });

    newRow.hide().insertBefore('#productAddFormRow').show('slow');
}

function searchProductByTerm(term, resultCallback) {
    $.getJSON('/api/products', term)
     .done((products) => {
        resultCallback(products.map((p) => {
            return {
                id: p.id,
                label: p.name + ' ' + p.price + '$',
                value: p.name
            }
        }))
     })
}