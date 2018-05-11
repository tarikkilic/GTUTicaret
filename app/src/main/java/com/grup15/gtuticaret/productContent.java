package com.grup15.gtuticaret;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class productContent extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Stack<Comment> comments;
    TextView lastCommentText;
    TextView lastCommentDate;
    TextView lastCommentUser;

    Product newProduct;
    User currentUser;
    User userComesProduct;
    LinearLayout lastCommentLL;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_content);
        lastCommentLL = findViewById(R.id.lastCommentLL);

        newProduct = (Product) getIntent().getExtras().getSerializable("pro");
        int image = getApplicationContext().getResources().getIdentifier(newProduct.getImageCode(), "drawable", getPackageName());

        String name = newProduct.getName();
        Double price = newProduct.getPrice();
        String feature = newProduct.getFeatures();


        //Kullanıcıya geçici yorumlar yerleştirildi.
        comments = new Stack<>();
        comments.add(new Comment("okula böyle adamlar lazım teşekkürler", "01/01/2017", "burhanElgun"));
        comments.add(new Comment("içinde tel olmayan jumper sattı", "04/05/2017", "ramazanGuvenc"));
        comments.add(new Comment("Aldığım tüm ürünlerinden memnunum", "19/09/2017", "emirhanKaragozoglu"));
        comments.add(new Comment("Paramı alıp kaçtı güvenmeyin", "12/02/2018", "tarikKilic"));
        comments.add(new Comment("Adamın dibi yok böyle insan", "24/04/2018", "akinCam"));
        comments.add(new Comment("GTU Alışveriş sağolsun sizin gibi dürüst satıcıların olduğunu görebildik. Dünya böyle satıcılar uğruna dönüyor", "30/04/2018", "celalCanKaya"));


        currentUser = new User("ssorman");
        userComesProduct = new User("newUser", comments);

        ImageView productImage = findViewById(R.id.productImage);
        productImage.setImageResource(image);

        TextView productName = findViewById(R.id.productName);
        productName.setText(name);

        TextView productPrice = findViewById(R.id.productCost);
        productPrice.setText(price.toString() + "TL");

        TextView productFeatures = findViewById(R.id.features);
        productFeatures.setText(feature);


        view = getLayoutInflater().inflate(R.layout.comment_row, null);

        //yorumların ilk elemanı ekranda gösterilecek yorum son yapılan yorumdur.

        lastCommentText = view.findViewById(R.id.comment);
        lastCommentDate = view.findViewById(R.id.commentDate);
        lastCommentUser = view.findViewById(R.id.userName);

        lastCommentText.setText(userComesProduct.getComments().peek().getCommentText());
        lastCommentDate.setText(userComesProduct.getComments().peek().getCommentDate());
        lastCommentUser.setText(userComesProduct.getComments().peek().getUserName());
        lastCommentLL.addView(view);


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }

        });
        menuBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basketmenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()) {
            case R.id.basket:
                Intent i = new Intent(this, Sepet.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Yorum yapa tıklandığında yeni yapılan yorumu ürün yorumlarının sonuna ekleme
    public void takeComment(View v) {

       EditText editComment = findViewById(R.id.editComment);
       ArrayList<TextView> comment = new ArrayList<>();
       comment.add(lastCommentText);
       comment.add(lastCommentDate);
       comment.add(lastCommentUser);

       currentUser.comment(comment,userComesProduct,editComment);

    }

    // Yorumlar yeni ekranda gösterilir.
    public void showAllComments(View v) {
        Intent intent = new Intent(getApplicationContext(), commentsPage.class);
        ArrayList<Comment> arrComment = new ArrayList<>();
        arrComment.addAll(userComesProduct.getComments());
        intent.putExtra("comments", arrComment);
        startActivity(intent);
    }

    public void addToCart(View v) {
        if(currentUser.addToCart(newProduct))
            Toast.makeText(getApplicationContext(),"Ürün sepete eklendi.",
                    Toast.LENGTH_SHORT).show();
        else{
            new AlertDialog.Builder(this)
                    .setTitle("UYARI!")
                    .setCancelable(false)
                    .setMessage("Sepetine aynı üründen birden fazla ekleyemezsin.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }

    public void menuBar(){

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.Black));

        NavigationView navigation = (NavigationView) findViewById(R.id.toolbar);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_menu:
                        Intent menu = new Intent(productContent.this, AnaEkran.class);
                        startActivity(menu);
                        finish();
                        break;
                    case R.id.navigation_account:
                        Intent hesap = new Intent(productContent.this, Hesabim.class);
                        startActivity(hesap);
                        finish();
                        break;
                    case R.id.navigation_categories:
                        Intent kategori = new Intent(productContent.this, Categories.class);
                        startActivity(kategori);
                        finish();
                        break;
                    case R.id.navigation_setting:
                        Intent ayarlar = new Intent(productContent.this, Ayarlar.class);
                        startActivity(ayarlar);
                        finish();
                        break;
                }
                return false;
            }
        });
    }
}
