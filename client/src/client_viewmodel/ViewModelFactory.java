package client_viewmodel;

import client_model.Model;

public class ViewModelFactory {
  private LoginViewModel loginViewModel;
  private MainPageViewModel mainPageViewModel;
  private SeatMappingViewModel seatMappingViewModel;
  private RegisterPageViewModel registerPageViewModel;

  public ViewModelFactory(Model model) {
    this.mainPageViewModel = new MainPageViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.registerPageViewModel = new RegisterPageViewModel(model);
    this.seatMappingViewModel = new SeatMappingViewModel(model);
  }

  public LoginViewModel getLoginViewModel() {
    return loginViewModel;
  }

  public MainPageViewModel getMainPageViewModel() {
    return mainPageViewModel;
  }

  public RegisterPageViewModel getRegisterPageViewModel() {
    return registerPageViewModel;
  }

  public SeatMappingViewModel getSeatMappingViewModel() { // Added opening curly brace
    return seatMappingViewModel;
  } // Added closing curly brace
}
