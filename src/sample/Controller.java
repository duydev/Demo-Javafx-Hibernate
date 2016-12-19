package sample;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;

public class Controller {

    private SessionFactory sf;
    private boolean addAct = false;

    public Controller() {
        try {
            sf = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    TextField txtMaLop;

    @FXML
    TextField txtTenLop;

    @FXML
    TextField txtSiSo;

    @FXML
    Button btnAdd;

    @FXML
    Button btnEdit;

    @FXML
    Button btnDelete;

    @FXML
    Button btnSave;

    @FXML
    Button btnNotSave;

    @FXML
    private TableView<LopEntity> tableView;

    @FXML
    private TableColumn<LopEntity, String> colMaLop;

    @FXML
    private TableColumn<LopEntity, String> colTenLop;

    @FXML
    private TableColumn<LopEntity, Integer> colSiSo;

    @FXML
    private void initialize(){
        colMaLop.setCellValueFactory(new PropertyValueFactory<LopEntity, String>("maLop"));
        colTenLop.setCellValueFactory(new PropertyValueFactory<LopEntity, String>("tenLop"));
        colSiSo.setCellValueFactory(new PropertyValueFactory<LopEntity, Integer>("siSo"));
        updateTableData();
        lockInput(true);
        lockButton(false);
    }

    @FXML
    private void Thoat(MouseEvent evt) {
        this.sf.close();
        Button btnThoat = (Button) evt.getSource();
        Stage stage = (Stage) btnThoat.getScene().getWindow();
        stage.close();
    }

    @FXML void addData(MouseEvent evt){
        this.addAct = true;
        lockInput(false);
        lockButton(true);
    }

    @FXML void editData(MouseEvent evt){
        this.addAct = false;
    }

    @FXML void deleteData(MouseEvent evt){

    }

    @FXML
    private void saveData(MouseEvent evt){
        String MaLop = txtMaLop.getText();
        String TenLop = txtTenLop.getText();
        int SiSo = 0;
        try {
            SiSo = Integer.parseInt(txtSiSo.getText());
        } catch (NumberFormatException ex){
            return;
        }
        try {
            Session s = sf.openSession();
            s.beginTransaction();
            if( addAct ) {
                LopEntity lopEntity = new LopEntity();
                lopEntity.setMaLop(MaLop);
                lopEntity.setTenLop(TenLop);
                lopEntity.setSiSo(SiSo);
                s.save(lopEntity);
            } else {

            }
            s.getTransaction().commit();
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setContentText("Lưu thành công!");
            alert.showAndWait();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            updateTableData();
            lockInput(true);
            lockButton(false);
        }
    }

    @FXML
    private void notSaveData(MouseEvent evt){
        lockInput(true);
        lockButton(false);
    }

    private void updateTableData(){
        Session s = sf.openSession();
        s.beginTransaction();
        List list = s.createQuery("FROM LopEntity").list();
        s.getTransaction().commit();
        s.close();
        ObservableList<LopEntity> observableList = FXCollections.observableList(list);
        tableView.setItems(observableList);
    }

    private void clearForm(){
        this.txtMaLop.setText(null);
        this.txtTenLop.setText(null);
        this.txtSiSo.setText(null);
    }

    private void lockInput(boolean a){
        this.txtMaLop.setDisable(a);
        this.txtTenLop.setDisable(a);
        this.txtSiSo.setDisable(a);
    }

    private void lockButton(boolean a){
        this.btnAdd.setDisable(a);
        this.btnEdit.setDisable(a);
        this.btnDelete.setDisable(a);
        this.btnSave.setDisable(!a);
        this.btnNotSave.setDisable(!a);
    }


}
