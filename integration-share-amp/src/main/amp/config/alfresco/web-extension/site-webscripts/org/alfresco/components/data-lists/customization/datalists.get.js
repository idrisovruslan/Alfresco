// Find the default DataLists widget and replace it with the custom widget
for (var i=0; i<model.widgets.length; i++) {
  if (model.widgets[i].id == "DataLists") {
    model.widgets[i].name = "Smart.custom.dataLists";
  }
}