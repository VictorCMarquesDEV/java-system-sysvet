package exceptions;

public class GenericException extends Exception {

  public GenericException(){
    super("Ocorreu um erro!");
  }

  public GenericException(String message){
    super(message);
  }

  @Override
  public String getMessage(){
    return super.getMessage();
  }
} 
