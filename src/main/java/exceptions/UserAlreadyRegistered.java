package exceptions;

public class UserAlreadyRegistered extends Exception {
  @Override
  public String getMessage(){
    return "Já existe um funcionário com este CPF!";
  }
} 
