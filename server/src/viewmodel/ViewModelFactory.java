package viewmodel;

import model.Model;

import java.rmi.RemoteException;

public class ViewModelFactory
{

  private MainPageViewModel pageViewModel;
  private LoginViewModel loginViewModel;
  private RegisterPageViewModel registerViewModel;
  private AdminPageViewModel adminPageViewModel;
  private SeatMappingViewModel seatMappingViewModel;
  private TransitionPageViewModel transitionPageViewModel;
  private SnackSelectionViewModel snackSelectionViewModel;
  private TicketConfirmationViewModel ticketConfirmationViewModel;
  private OrderConfirmationViewModel orderConfirmationViewModel;
  private OrderDetailsViewModel orderDetailsViewModel;
  private ManageViewModel manageViewModel;
  private AddMovieViewModel addMovieViewModel;
  private AddScreeningViewModel addScreeningViewModel;
  private EditPricesViewModel editPricesViewModel;
  private ViewState viewState;

  public ViewModelFactory(Model model) throws RemoteException
  {
    this.viewState = new ViewState();
    this.pageViewModel = new MainPageViewModel(model,viewState);
    this.registerViewModel = new RegisterPageViewModel(model,viewState);
    this.loginViewModel = new LoginViewModel(model, viewState);
    this.seatMappingViewModel = new SeatMappingViewModel(model, viewState);
    this.snackSelectionViewModel = new SnackSelectionViewModel(model,viewState);
    this.ticketConfirmationViewModel = new TicketConfirmationViewModel(model, viewState);
    this.transitionPageViewModel = new TransitionPageViewModel(model, viewState);
    this.manageViewModel= new ManageViewModel(model, viewState);
    this.orderConfirmationViewModel = new OrderConfirmationViewModel(model, viewState);
    this.orderDetailsViewModel = new OrderDetailsViewModel(model,viewState);
    this.addMovieViewModel= new AddMovieViewModel(model, viewState);
    this.adminPageViewModel = new AdminPageViewModel(model, viewState);
    this.addScreeningViewModel= new AddScreeningViewModel(model, viewState);
    this.adminPageViewModel= new AdminPageViewModel(model, viewState);
    this.editPricesViewModel= new EditPricesViewModel(model, viewState);


  }
  public OrderDetailsViewModel getOrderDetailsViewModel()
  {
    return orderDetailsViewModel;
  }

  public MainPageViewModel getPageViewModel()
  {
    return pageViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }
  public OrderConfirmationViewModel getOrderConfirmationViewModel(){return orderConfirmationViewModel;}

  public ManageViewModel getManageViewModel()
  {
    return manageViewModel;
  }
  public RegisterPageViewModel getRegisterViewModel()
  {
    return registerViewModel;
  }
  public TransitionPageViewModel getTransitionPageViewModel()
  {
    return transitionPageViewModel;
  }
  public SeatMappingViewModel getSeatMappingViewModel()
  {
    return seatMappingViewModel;
  }
  public SnackSelectionViewModel getSnackSelectionViewModel()
  {
    return snackSelectionViewModel;
  }
  public TicketConfirmationViewModel getTicketConfirmationViewModel()
  {
    return ticketConfirmationViewModel;
  }
  public AddScreeningViewModel getAddScreeningViewModel()
  {
    return addScreeningViewModel;
  }
  public AddMovieViewModel getAddMovieViewModel()
  {
    return addMovieViewModel;
  }
  public EditPricesViewModel getEditPricesViewModel()
  {
    return editPricesViewModel;
  }
  public AdminPageViewModel getAdminPageViewModel()
  {
    return  adminPageViewModel;
  }


}

