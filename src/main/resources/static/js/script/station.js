rules = {
    companySelect2: "required",
    name: "required",
    latitude: {
        required:true,
        number: true
    },
    longitude: {
        required:true,
        number: true
    }
};

messages = {
    companySelect2: resources.pleaseSelect.format(resources.company),
    name: resources.pleaseEnter.format(resources.name),
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
    let model = {
        id: isNullOrEmpty($("#hdf_id").val()) ? null : $("#hdf_id").val(),
        company: isNullOrEmpty($("#companySelect2").val())? null : {id: $("#companySelect2").val()},
        companyId: $("#companySelect2").val(),
        name: $("#name").val(),
        latitude: $("#latitude").val(),
        longitude: $("#longitude").val(),
    };
    return model;
}

columns = [{
    data: 'name'
}, {
    data: 'latitude'
}, {
    data: 'longitude'
}, {
    data: 'company.name'
}, {
    data: 'modifiedDate',
    render: function (data) { return toLocalizingDateString(data) }
}, {
    data: 'id',
    searchable: false,
    sortable: false,
    render: function (data) { return "<a class='btn btn-default fa fa-pencil' id='" + data + "'></a> <a class='btn btn-danger fa fa-trash' id='" + data + "'></a>" }
}];

function loadInputByEntity(model) {
    $("#hdf_id").val(model.id);
    $("#companySelect2").html("<option value='" + get(() => model.company.id) + "' selected>" + get(() => model.company.name) + "</option>").trigger('change');
    $("#companySelect2").val(get(() => model.company.id)).trigger('change');
    $('#name').val(model.name);
    $('#latitude').val(model.latitude);
    $("#longitude").val(model.longitude);

}
