package server_viewmodel;

public class ViewState
{
private SimpleScreeningView selectedScreening;
public SimpleScreeningView getSelectedScreening(){return selectedScreening;}
public void setSelectedScreening(SimpleScreeningView screening){
  this.selectedScreening = screening;
}
}
