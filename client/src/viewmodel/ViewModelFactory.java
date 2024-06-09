package viewmodel;

import model.Model;

import java.rmi.RemoteException;
/**
 * Factory class responsible for creating  ViewModel objects used in the  system.
 *   Each ViewModel is associated with their corresponding view in the application.

 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
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
  /**
   * Constructor for ViewModelFactory class.
   * Initializes all ViewModel objects with the provided Model instance and ViewState.
   *
   * @param model The Model instance used by ViewModel objects.
   * @throws RemoteException If there is an issue with remote method invocation.
   */
  public ViewModelFactory(Model model) throws RemoteException
  {
    this.viewState = new ViewState();
    this.pageViewModel = new MainPageViewModel(model,viewState);
    this.registerViewModel = new RegisterPageViewModel(model, viewState);
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
  /**
   * Retrieves the OrderDetailsViewModel object.
   *
   * @return The OrderDetailsViewModel object associated with the order details view.
   */
  public OrderDetailsViewModel getOrderDetailsViewModel()
  {
    return orderDetailsViewModel;
  }
  /**
   * Retrieves the MainPageViewModel object.
   *
   * @return The MainpageViewModel object associated with themain page details view.
   */
  /**
   * follow the exact same logic as the first 2 commented
   * @return
   */
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

