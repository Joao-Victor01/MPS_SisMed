package SisMed.observer;

import java.util.ArrayList;

public interface EmailObserver {
    void enviarEmail(String tipo, ArrayList<String> dados);
}