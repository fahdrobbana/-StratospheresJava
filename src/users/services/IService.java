/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.services;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Andrew
 */
public interface IService<T> {
    public void Ajouter(T t );
  //  public void Ajouter2(T t);
    public void Modifier(T t);
    public void Supprimer(T t);
    public ObservableList<T> readAnnonce();
     public ObservableList<T> readcomm();
   public ArrayList<T> Afficher();
}
