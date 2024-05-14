package client_viewmodel;

import client_model.Model;

public class ViewModelFactory
{

  private MainPageViewModel pageViewModel;
  private LoginViewModel loginViewModel;
  private RegisterPageViewModel registerViewModel;
  private SeatMappingViewModel seatMappingViewModel;
  private TicketBookingViewModel ticketBookingViewModel;
  private BookingConfirmedViewModel bookingConfirmedViewModel;

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
  public BookingConfirmedViewModel getBookingConfirmedViewModel()
  {
    return bookingConfirmedViewModel;
  }
  public SeatMappingViewModel getSeatMappingViewModel()
  {
    return seatMappingViewModel;
  }

}
