package br.com.gusleite.NexGenTech.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String s){
        super(s);
    }
}
