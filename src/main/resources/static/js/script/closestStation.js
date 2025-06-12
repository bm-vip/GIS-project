var offlineDataTable;

rules = {
    latitude: {
        required: true,
        number: true
    },
    longitude: {
        required: true,
        number: true
    }
};

messages = {
    latitude: {
        required: resources.pleaseEnter.format(resources.latitude),
        number: resources.mustBeNumber.format(resources.latitude)
    },
    longitude: {
        required: resources.pleaseEnter.format(resources.longitude),
        number: resources.mustBeNumber.format(resources.longitude)
    }
};


function loadEntityByInput() {
    return {};
}

columns = [{
    render: function (data, type, full, meta) { return meta.row+1; }
}, {
    data: 'name'
}, {
    data: 'latitude'
}, {
    data: 'longitude'
}, {
    data: 'company.name'
}, {
    data: 'distance',
}];

function submitForm(form) {
    $.blockUI(blockUiOptions());
    $.ajax({
        type: "GET",
        url: ajaxUrl + "/findClosest-by-geopoint?latitude={0}&longitude={1}&maxDistance={2}&page={3}&size={4}".format($("#latitude").val(),$("#longitude").val(),parseFloat($("#distance").val())*1000.0,0,offlineDataTable.page.len()),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: { companyId: $("#companySelect2").val() },
        success: function (data) {
            $.unblockUI();
            if (data.error == null) {
                offlineDataTable.clear().rows.add(data.content).draw();
            } else {
                show_error(data.error);
            }
        },
        error: function (header, status, error) {
            show_error('ajax answer GET returned error: ' + header + ' ' + status + ' ' + error);
        }
    });
}

function loadTable() {
    offlineDataTable = initTable('.table:eq(0)', columns, []);
}