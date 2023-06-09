package projek.akhir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projek.akhir.Models.Admin;
import projek.akhir.Models.Diskon;
import projek.akhir.Models.Produk;
import projek.akhir.Models.ProdukDipesan;

public class FooTure extends Application {
    private Stage primaryStage;

    DropShadow dropShadow = new DropShadow();
    BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(5), null);
    Background background = new Background(backgroundFill);

    BackgroundFill backgroundFill2 = new BackgroundFill(Color.BLACK, null, null);
    Background background2 = new Background(backgroundFill);

    List<Produk> listProduk = new ArrayList<>();
    List<ProdukDipesan> listProdukDipesan = new ArrayList<>();

    List<Diskon> listDiskon = new ArrayList<>();
    HashMap<Integer, Integer> hargaProduk = new HashMap<>();

    private int hargaDiskon;
    private int hasilAkhir = 0;
    private int antrianAwal = 0;

    void initProduk() {
        Produk produk1 = new Produk();
        produk1.setId(1);
        produk1.setNama("Menu 1");

        Produk produk2 = new Produk();
        produk2.setId(2);
        produk2.setNama("Menu 2");

        Produk produk3 = new Produk();
        produk3.setId(3);
        produk3.setNama("Menu 3");

        Produk produk4 = new Produk();
        produk4.setId(4);
        produk4.setNama("Menu 4");

        listProduk.add(produk1);
        listProduk.add(produk2);
        listProduk.add(produk3);
        listProduk.add(produk4);

        hargaProduk.put(produk1.getId(), 10000);
        hargaProduk.put(produk2.getId(), 15000);
        hargaProduk.put(produk3.getId(), 15000);
        hargaProduk.put(produk4.getId(), 20000);        
    };

    void initPesanan() {
        for (Produk produk : listProduk) {
            ProdukDipesan produkDipesan = new ProdukDipesan();
            produkDipesan.setProduk(produk);
            produkDipesan.setKuantitas(0);
            listProdukDipesan.add(produkDipesan);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        if (listProdukDipesan.size() == 0) {
            initProduk();
            initPesanan();
        }

        Label label = new Label("Nama Restaurant");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(50);
        label.setPrefWidth(250);
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        Button startButton = new Button("PESAN SEKARANG");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(50);
        startButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        startButton.setOnAction(event -> {
            showMenu();
            for (ProdukDipesan produkDipesan : listProdukDipesan) {
                produkDipesan.setKuantitas(0);
            }
        });

        Button loginButton = new Button("Login as Admin");
        loginButton.setPrefWidth(150);
        loginButton.setPrefHeight(30);
        loginButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
        loginButton.setOnAction(event -> {
            Stage loginScene = new Stage();
            Scene scene6 = showLoginMenu();
            loginScene.setScene(scene6);
            loginScene.setTitle("Login Admin");
            loginScene.show();
        });

        label.setEffect(dropShadow);
        startButton.setEffect(dropShadow);
        label.setBackground(background);
        startButton.setBackground(background);
        loginButton.setBackground(background);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label, startButton, loginButton);

        Scene scene1 = new Scene(root, 600, 500);
        primaryStage.setTitle("FooTure");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private void showMenu() {
        Label label = new Label("Daftar Menu");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(50);
        label.setPrefWidth(250);
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(label);

        Button confirmButton = new Button("CONFIRM");
        confirmButton.setPrefWidth(160);
        confirmButton.setPrefHeight(40);
        confirmButton.setStyle("-fx-font-size: 25px; -fx-text-fill: white;");
        confirmButton.setDisable(true);

        if (listProdukDipesan.size() > 0) {
            confirmButton.setOnAction(e -> {
                Stage detailPesananScene = new Stage();
                Scene scene2 = detailPesananScene();
                detailPesananScene.setScene(scene2);
                detailPesananScene.setTitle("FooTure Detail");
                detailPesananScene.show();
            });
        }

        for (ProdukDipesan produkDipesan : listProdukDipesan) {
            Label menu = new Label("  " + produkDipesan.getProduk().getNama() + "\t\t| "
                    + hargaProduk.get(produkDipesan.getProduk().getId()) / 1000 + "K");
            menu.setAlignment(Pos.CENTER);
            menu.setPrefHeight(50);
            menu.setPrefWidth(250);
            menu.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

            Button kurang = new Button("-");
            kurang.setAlignment(Pos.CENTER);
            kurang.setPrefHeight(50);
            kurang.setPrefWidth(50);
            kurang.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");

            Label input = new Label("0");
            input.setId(String.valueOf(produkDipesan.getProduk().getId()));
            input.setAlignment(Pos.CENTER);
            input.setPrefWidth(100);
            input.setPrefHeight(50);
            input.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

            kurang.setOnAction(e -> {
                int jumlahPesanan = Integer.parseInt(input.getText());
                if (jumlahPesanan > 0) {
                    input.setText(String.valueOf(jumlahPesanan - 1));
                    produkDipesan.kurangPesanan();
                }

                boolean adaKuantitas = false;
                for (ProdukDipesan produkDipesan1 : listProdukDipesan) {
                    if (produkDipesan1.getKuantitas() > 0)
                        adaKuantitas = true;
                }
                if (!adaKuantitas) {
                    confirmButton.setDisable(true);
                }
            });

            Button tambah = new Button("+");
            tambah.setAlignment(Pos.CENTER);
            tambah.setPrefHeight(50);
            tambah.setPrefWidth(50);

            tambah.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");
            tambah.setOnAction(e -> {
                int jumlahPesanan = Integer.parseInt(input.getText());
                input.setText(String.valueOf(jumlahPesanan + 1));
                produkDipesan.tambahPesanan();

                boolean adaKuantitas = false;
                for (ProdukDipesan produkDipesan1 : listProdukDipesan) {
                    if (produkDipesan1.getKuantitas() > 0)
                        adaKuantitas = true;
                    if (adaKuantitas) {
                        confirmButton.setDisable(false);
                        break;
                    }
                }
            });

            menu.setBackground(background2);
            kurang.setBackground(background2);
            input.setBackground(background2);
            tambah.setBackground(background2);

            HBox baris = new HBox(3);
            baris.getChildren().addAll(menu, kurang, input, tambah);
            root.getChildren().add(baris);
        }

        Button backButton = new Button("BACK");
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(40);
        backButton.setStyle("-fx-font-size: 25px; -fx-text-fill: white;");

        backButton.setOnAction(e -> {
            start(primaryStage);
        });

        Button clearButton = new Button("CLEAR");
        clearButton.setPrefWidth(160);
        clearButton.setPrefHeight(40);
        clearButton.setStyle("-fx-font-size: 25px; -fx-text-fill: white;");
        clearButton.setOnAction(event -> {
            for (ProdukDipesan produkDipesan : listProdukDipesan) {
                Node node = root.lookup("#" + produkDipesan.getProduk().getId());
                if (node instanceof Label) {
                    Label input = (Label) node;
                    input.setText("0");
                    produkDipesan.setKuantitas(0);
                    confirmButton.setDisable(true);
                }
            }
        });

        label.setBackground(background2);
        backButton.setBackground(background2);
        clearButton.setBackground(background2);
        confirmButton.setBackground(background2);

        HBox menuButton = new HBox(20);
        menuButton.getChildren().addAll(backButton, clearButton, confirmButton);
        root.getChildren().add(menuButton);

        Scene scene2 = new Scene(root, 600, 500);

        primaryStage.setScene(scene2);
    }

    private void showAdminScene() {
        Label label = new Label("ADMIN MENU");
        label.setAlignment(Pos.CENTER);
        label.setMaxHeight(1000);
        label.setMaxWidth(350);
        label.setStyle("-fx-font-size: 25px; -fx-text-fill: white;");

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label);

        Button logoutButton = new Button("Logout");
        logoutButton.setPrefWidth(130);
        logoutButton.setPrefHeight(40);
        logoutButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> {
            start(primaryStage);
        });

        Button promoButton = new Button("Promo");
        promoButton.setPrefWidth(130);
        promoButton.setPrefHeight(40);
        promoButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        promoButton.setOnAction(event -> {
            Stage ubahPromo = new Stage();
            Scene scene10 = ubahPromoScene();
            ubahPromo.setScene(scene10);
            ubahPromo.setTitle("Kode Promo");
            ubahPromo.show();
        });

        Button antrianButton = new Button("Antrian");
        antrianButton.setPrefWidth(130);
        antrianButton.setPrefHeight(40);
        antrianButton.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        antrianButton.setOnAction(event -> {
            Stage antrianScene = new Stage();
            Scene scene8 = showAntrianScene();
            antrianScene.setScene(scene8);
            antrianScene.setTitle("Nomor Antrian");
            antrianScene.show();
        });

        HBox menu = new HBox(20);
        menu.getChildren().addAll(logoutButton, promoButton, antrianButton);
        menu.setAlignment(Pos.CENTER);
        root.getChildren().add(menu);

        label.setBackground(background);
        promoButton.setBackground(background);
        antrianButton.setBackground(background);
        logoutButton.setBackground(background);

        Scene scene2 = new Scene(root,500, 150);

        primaryStage.setScene(scene2);
    }

    private Scene detailPesananScene() {
        Label label = new Label("Detail Pesanan");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(300);
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        VBox root = new VBox(5);
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(label);

        int totalHarga = 0;
        for (ProdukDipesan produkDipesan : listProdukDipesan) {
            int hargaMenu = hargaProduk.get(produkDipesan.getProduk().getId());
            int jumlahMenu = produkDipesan.getKuantitas();
            if (jumlahMenu > 0) {
                int totalHargaMenu = hargaMenu * jumlahMenu;
                totalHarga += totalHargaMenu;
                hasilAkhir = totalHarga;

                Text detail = detailLabel(jumlahMenu, produkDipesan.getProduk().getNama(), totalHargaMenu);
                // detail.setAlignment(Pos.CENTER);
                // detail.setPrefHeight(30);
                // detail.setPrefWidth(300);
                // detail.setBackground(background);
                detail.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
                root.getChildren().add(detail);
            }
        }

        Label total = new Label(" Total \t\t| Rp" + String.valueOf(totalHarga));
        total.setAlignment(Pos.CENTER);
        total.setPrefHeight(40);
        total.setPrefWidth(300);
        total.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Button batalButton = new Button("Batal");
        batalButton.setPrefWidth(60);
        batalButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
        batalButton.setOnAction(event -> {
            showMenu();
            for (ProdukDipesan produkDipesan : listProdukDipesan) {
                produkDipesan.setKuantitas(0);
            }
        });

        int totalHargaAkhir = totalHarga;
        Button promoButton = new Button("Promo");
        promoButton.setPrefWidth(100);
        promoButton.setPrefHeight(30);
        promoButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        if (listDiskon.size() == 0) {
            promoButton.setDisable(true);
        } else {
            promoButton.setOnAction(event -> {
                Stage promoScene = new Stage();
                Scene scene3 = promoScene(totalHargaAkhir, total);
                promoScene.setScene(scene3);
                promoScene.setTitle("Promo");
                promoScene.show();
            });
        }

        Button bayarButton = new Button("BAYAR");
        bayarButton.setPrefWidth(100);
        bayarButton.setPrefHeight(30);
        bayarButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
        bayarButton.setOnAction(event -> {
            Stage bayarScene = new Stage();
            Scene scene4 = bayarScene(hasilAkhir);
            bayarScene.setScene(scene4);
            bayarScene.setTitle("Pembayaran");
            bayarScene.show();
            antrianAwal += 1;
        });

        HBox menu = new HBox(20);
        menu.getChildren().addAll(batalButton, promoButton, bayarButton);

        root.getChildren().add(total);
        root.getChildren().add(menu);
        label.setBackground(background2);
        batalButton.setBackground(background2);
        promoButton.setBackground(background2);
        total.setBackground(background2);
        bayarButton.setBackground(background2);

        Scene scene3 = new Scene(root);
        primaryStage.setScene(scene3);

        return new Scene(root, 500, 450);
    }

    private Text detailLabel(int jumlahMenu, String namaMenu, int hargaMenu) {
        Text detail = new Text("x" + jumlahMenu + "\t" + namaMenu + "\t| Rp" + hargaMenu);
        // detail.setAlignment(Pos.CENTER);
        // detail.setPrefHeight(30);
        // detail.setPrefWidth(300);
        detail.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        return detail;
    }

    private Scene promoScene(int totalHargaAkhir, Label total) {
        Label label = new Label("Masukkan Kode Promo");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(200);
        label.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        TextField inputKode = new TextField();
        inputKode.setAlignment(Pos.CENTER);
        inputKode.setMaxWidth(120);
        inputKode.setMaxHeight(40);
        inputKode.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        Button okButton = new Button("OK");
        okButton.setAlignment(Pos.CENTER);
        okButton.setPrefHeight(40);
        okButton.setPrefWidth(40);
        okButton.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");
        okButton.setOnAction(event -> {
            for (Diskon diskon : listDiskon) {
                String userInputKode = inputKode.getText();
                if (diskon.getKodePromo().equals(userInputKode)) {
                    hargaDiskon = totalHargaAkhir * diskon.getPersen() / 100;
                    hasilAkhir = totalHargaAkhir - hargaDiskon;
                    total.setText(" Total \t\t| Rp" + String.valueOf(hasilAkhir));

                    System.out.println("Anda menggunakan kode promo \"" + diskon.getKodePromo() + "\" dan mendapat potongan sebesar " + diskon.getPersen() + "%");
                }
            }
            total.setText(" Total \t\t| Rp" + String.valueOf(hasilAkhir));
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            Stage promoBerhasilScene = new Stage();
            Scene scene4 = promoBerhasilScene();
            promoBerhasilScene.setScene(scene4);
            promoBerhasilScene.setTitle("Promo");
            promoBerhasilScene.show();
        });

        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(inputKode, okButton);

        VBox root = new VBox(20);
        root.getChildren().addAll(label, hbox);
        root.setAlignment(Pos.CENTER);

        label.setBackground(background);
        okButton.setBackground(background);
        return new Scene(root, 200, 100);
    }

    private Scene showLoginMenu() {
        Admin admin = new Admin();
        admin.setUsername("username");
        admin.setPassword("password");

        Label label = new Label("Login Menu");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(150);
        label.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        TextField inputUsername = new TextField();
        inputUsername.setPromptText("Username");
        inputUsername.setAlignment(Pos.CENTER);
        inputUsername.setMaxWidth(120);
        inputUsername.setMaxHeight(40);
        inputUsername.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        TextField inputPassword = new TextField();
        inputPassword.setPromptText("Password");
        inputPassword.setAlignment(Pos.CENTER);
        inputPassword.setMaxWidth(120);
        inputPassword.setMaxHeight(40);
        inputPassword.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        Button loginButton = new Button("Login");
        loginButton.setAlignment(Pos.CENTER);
        loginButton.setPrefHeight(30);
        loginButton.setPrefWidth(70);
        loginButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
        loginButton.setOnAction(event -> {
            String userInputUsername = inputUsername.getText();
            String userInputPassword = inputPassword.getText();
            if (userInputUsername.equals(admin.getUsername()) && userInputPassword.equals(admin.getPassword())) {
                Stage loginMenu = (Stage) loginButton.getScene().getWindow();
                loginMenu.close();
                showAdminScene();
            }
        });

        VBox root = new VBox(5);
        root.getChildren().addAll(label, inputUsername, inputPassword, loginButton);
        root.setAlignment(Pos.CENTER);

        label.setBackground(background);
        loginButton.setBackground(background);
        return new Scene(root, 300, 150);
    }

    private Scene bayarScene(int hasilAkhir) {
        Label label = new Label("Masukkan Nominal Pembayaran");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(300);
        label.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");

        TextField inputPembayaran = new TextField();
        inputPembayaran.setAlignment(Pos.CENTER);
        inputPembayaran.setMaxWidth(120);
        inputPembayaran.setMaxHeight(40);
        inputPembayaran.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        Button okButton = new Button("OK");
        okButton.setAlignment(Pos.CENTER);
        okButton.setPrefHeight(40);
        okButton.setPrefWidth(40);
        okButton.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");
        okButton.setOnAction(event -> {
            int userInput = Integer.parseInt(inputPembayaran.getText());
            if (userInput >= hasilAkhir) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
                Stage bayarBerhasilScene = new Stage();
                Scene scene5 = bayarBerhasilScene();
                bayarBerhasilScene.setScene(scene5);
                bayarBerhasilScene.setTitle("Selesai");
                start(primaryStage);
                bayarBerhasilScene.show();
                System.out.println("Anda mendapat nomor antrian " + antrianAwal);

            } else {
                Stage bayarGagalScene = new Stage();
                Scene scene6 = bayarGagalScene();
                bayarGagalScene.setScene(scene6);
                bayarGagalScene.setTitle("Gagal");
                bayarGagalScene.show();
            }
        });

        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(inputPembayaran, okButton);

        VBox root = new VBox(20);
        root.getChildren().addAll(label, hbox);
        root.setAlignment(Pos.CENTER);

        label.setBackground(background);
        okButton.setBackground(background);
        return new Scene(root, 350, 100);
    }

    private Scene promoBerhasilScene() {
        Label label = new Label("Anda Mendapat Diskon Rp" + hargaDiskon);
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(300);
        label.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        VBox root = new VBox(20);
        root.getChildren().addAll(label);
        root.setAlignment(Pos.CENTER);

        label.setBackground(background2);
        return new Scene(root, 400, 50);
    }

    private Scene bayarBerhasilScene() {
        Label label = new Label("Pemesanan Berhasil");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(300);
        label.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        VBox root = new VBox(20);
        root.getChildren().addAll(label);
        root.setAlignment(Pos.CENTER);

        label.setBackground(background2);
        return new Scene(root, 400, 50);
    }

    private Scene bayarGagalScene() {
        Label label = new Label("Pembayaran Gagal");
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(30);
        label.setPrefWidth(300);
        label.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        VBox root = new VBox(20);
        root.getChildren().addAll(label);
        root.setAlignment(Pos.CENTER);

        label.setBackground(background2);
        return new Scene(root, 400, 50);
    }

    private Scene showAntrianScene() {
        String antrian = String.valueOf(antrianAwal);
        Label label = new Label(antrian);
        label.setAlignment(Pos.CENTER);
        label.setPrefHeight(100);
        label.setPrefWidth(100);
        label.setStyle("-fx-font-size: 80px; -fx-text-fill: white;");

        VBox root = new VBox(20);
        
        Button backButton = new Button("Back");
        backButton.setPrefWidth(60);
        backButton.setPrefHeight(30);
        backButton.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        backButton.setOnAction(e -> {
            showAdminScene();
        });

        Button editButton = new Button("Edit");
        editButton.setPrefWidth(60);
        editButton.setPrefHeight(30);
        editButton.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");
        editButton.setOnAction(event -> {
            antrianAwal = 0;
            Stage editScene = new Stage();
            Scene scene9 = showEditMenu();
            editScene.setScene(scene9);
            editScene.setTitle("Edit Antrian");
            editScene.show();
        });

        HBox menu = new HBox(10);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(backButton, editButton);

        root.getChildren().addAll(label, menu);
        root.setAlignment(Pos.CENTER);
        root.setBackground(background);

        Scene scene8 = new Scene(root, 200, 200);
        primaryStage.setScene(scene8);
        return new Scene(root);
    }

    private Scene showEditMenu() {
        TextField inputAntrian = new TextField();
        inputAntrian.setPromptText("Input Antrian");
        inputAntrian.setAlignment(Pos.CENTER);
        inputAntrian.setMaxWidth(120);
        inputAntrian.setMaxHeight(40);
        inputAntrian.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        Button ubahButton = new Button("Ganti");
        ubahButton.setAlignment(Pos.CENTER);
        ubahButton.setPrefHeight(30);
        ubahButton.setPrefWidth(70);
        ubahButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
        ubahButton.setOnAction(event -> {
            int userInputAntrian = Integer.parseInt(inputAntrian.getText());
            antrianAwal = userInputAntrian;
            showAntrianScene();
        });

        VBox root = new VBox(5);
        root.getChildren().addAll(inputAntrian, ubahButton);
        root.setAlignment(Pos.CENTER);

        inputAntrian.setBackground(background);
        ubahButton.setBackground(background);
        Scene scene3 = new Scene(root, 300, 150);
        primaryStage.setScene(scene3);

        return new Scene(root, 300, 150);
    }

    private Scene ubahPromoScene() {
        TextField inputKode = new TextField();
        inputKode.setPromptText("Kode Promo");
        inputKode.setAlignment(Pos.CENTER);
        inputKode.setMaxWidth(120);
        inputKode.setMaxHeight(40);
        inputKode.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        TextField inputPersen = new TextField();
        inputPersen.setPromptText("Besar Diskon");
        inputPersen.setAlignment(Pos.CENTER);
        inputPersen.setMaxWidth(120);
        inputPersen.setMaxHeight(40);
        inputPersen.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");

        Button backButton = new Button("BACK");
        backButton.setPrefWidth(60);
        backButton.setPrefHeight(40);
        backButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        backButton.setOnAction(e -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            showAdminScene();
        });

        Button okButton = new Button("OK");
        okButton.setAlignment(Pos.CENTER);
        okButton.setPrefHeight(40);
        okButton.setPrefWidth(60);
        okButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

        okButton.setOnAction(event -> {
            String adminInputKode = inputKode.getText();
            String valueInputPersen = inputPersen.getText();
            int adminInputPersen = Integer.parseInt(valueInputPersen);
            Diskon diskon = new Diskon();
            diskon.setKodePromo(adminInputKode);
            diskon.setPersen(adminInputPersen);
            listDiskon.add(diskon);

            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            ubahPromoScene();

            System.out.println("Kode Promo \"" + adminInputKode + "\" telah ditambahkan dengan potongan sebesar " + valueInputPersen);
        });

        HBox hbox1 = new HBox(20);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(inputKode, okButton);

        HBox hbox2 = new HBox(20);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(inputPersen, backButton);

        VBox root = new VBox(20);
        root.getChildren().addAll(hbox1, hbox2);
        root.setAlignment(Pos.CENTER);

        okButton.setBackground(background);
        backButton.setBackground(background);
        return new Scene(root, 250, 120);
    }
}