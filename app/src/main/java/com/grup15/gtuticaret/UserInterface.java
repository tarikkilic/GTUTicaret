package com.grup15.gtuticaret;


import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Serkan Sorman on 11.05.2018.
 */

public interface UserInterface {

    boolean addToCart(Product newProduct);
    void comment(ArrayList<TextView> comment,EditText editComment);
}
