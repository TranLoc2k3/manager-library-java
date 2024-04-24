package Client;

//import dao.SinhVienDao;
import dao.SinhVienDAO;
import ui.LoginForm;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    private static final String url = "rmi://localhost:1234/";
    public static void main(String[] args) throws Exception {
        LoginForm loginForm = new LoginForm();
    }
}
