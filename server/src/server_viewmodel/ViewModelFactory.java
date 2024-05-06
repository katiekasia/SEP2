package server_viewmodel;

import server_model.Model;
import server_view.RegisterViewController;

public class ViewModelFactory
{

  private MainPageViewModel pageViewModel;
  private LoginViewModel loginViewModel;
  private RegisterPageViewModel registerViewModel;
  private SeatMappingViewModel seatMappingViewModel;
  private TicketBookingViewModel ticketBookingViewModel;

  public ViewModelFactory(Model model)
  {
    this.pageViewModel = new MainPageViewModel(model);
    this.registerViewModel = new RegisterPageViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.seatMappingViewModel = new SeatMappingViewModel(model);
  }

  public MainPageViewModel getPageViewModel()
  {
    return pageViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public RegisterPageViewModel getRegisterViewModel()
  {
    return registerViewModel;
  }
  public TicketBookingViewModel getTicketBookingViewModel()
  {
    return ticketBookingViewModel;
  }
  public SeatMappingViewModel getSeatMappingViewModel()
  {
    return seatMappingViewModel;
  }

}

