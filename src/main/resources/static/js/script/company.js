rules = {
    name: "required",
    parentSelect2: "required"
};

messages = {
    name: resources.pleaseEnter.format(resources.name),
    parentSelect2: resources.pleaseSelect.format(resources.parent)
};
function loadEntityByInput() {
    let entity = {
        id: $("#hdf_id").val(),
        name: $("#name").val(),
        parent: {id: $("#parentSelect2").val()}
    };
    return entity;
}
columns = [{
    data: 'name'
}, {    
    data: 'parent.name',
    render: function (data, type, row) { return get(() => row.parent.name) }
}, {
    data: 'modifiedDate',
    render: function (data) { return toLocalizingDateString(data) }
},{
    data: 'id',
    searchable: false,
    sortable: false,
    render: function (data) { return "<a class='btn btn-default fa fa-pencil' id='" + data + "'></a> <a class='btn btn-danger fa fa-trash' id='" + data + "'></a>" }
}];

function loadInputByEntity(model) {
    $("#hdf_id").val(model.id);
    $('#name').val(model.name);
    $("#parentSelect2").html("<option value='" + get(() => model.parent.id) + "' selected>" + get(() => model.parent.name) + "</option>").trigger('change');
    $("#parentSelect2").val(get(() => model.parent.id)).trigger('change');
}