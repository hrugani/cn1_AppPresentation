/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.Accordion;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import java.util.Collection;


/**
 *
 * @author hrugani
 */
public class MyFormClients extends Form {
    
    PersistenceInterface p = PersistenceSto.getInstance();
    
    Container cCenter = new Container();
    
    public MyFormClients() {
        super(); 
        setTitle("Clientes");
       
        Toolbar toolBar = getToolbar();
        toolBar.addMaterialCommandToRightBar(
                   "", FontImage.MATERIAL_ADD, 6f,( ActionEvent e) -> {
                       
            int nextId = p.getNextId();
            p.addClient(
               new Client(
                       nextId,
                       "Cliente " + nextId,
                       30 + nextId,
                       Client.GEO_IBM
               )
            );
            
            populateScreen(cCenter);
            
        });
        
        toolBar.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_REFRESH, 6f, e -> {
            populateScreen(cCenter);
        });
                
        Button btnClose = new Button("Sair");
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    Display.getInstance().exitApplication();
                    
        });

        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, cCenter);
        addComponent(BorderLayout.SOUTH, btnClose);
       
        populateScreen(cCenter);
        
    }
    
    private void populateScreen(Container cnt) {
       cnt.removeAll();
       cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       cnt.setScrollableY(true);
       Collection<Client> clients = p.getClients();
       for(Client c : clients) {
           cnt.addComponent(new AccrClient(c));
       }
       cnt.repaint();
    }

    class AccrClient extends Accordion {

        Client c;
        MultiButton mb;
        
        public AccrClient(Client c) {
            super();
            this.c = c;
            addContent(
                this.mb = (MultiButton) createHeader(c), createDetail(c)
            );
        }
        
        public Client getClient() {
            return this.c;
        } 
        
        private Container createHeader(Client c) {
            MultiButton mbt = new MultiButton();
            mbt.setTextLine1(c.getName());
            mbt.setTextLine2(c.getAge() + " anos");
            return mbt;
        }
        
        private Container createDetail(Client c) {
            
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                        new Label("id:"),
                        new TextField("" + c.getId()),
                        new Label("Nome:"),
                        createTxtName(c),
                        new Label("Idade:"),
                        createTxtAge(c)
                )            
            );
            
            Container cButtons = new Container(new GridLayout(1,3));
            cButtons.addComponent(createBtnUpdate());
            cButtons.addComponent(createBtnDelete());
            cButtons.addComponent(createBtnWaze());
            
            cDetail.addComponent(BorderLayout.SOUTH, cButtons);
            
            return cDetail;
        }
        
        private TextField createTxtName(Client c) {
            final TextField txt = new TextField(c.getName());
            txt.addDataChangeListener((int type, int index) -> {
                mb.setTextLine1(txt.getText());
                c.setName(txt.getText());
            });
            return txt;
        }

        private TextField createTxtAge(Client c) {
            final TextField txt = new TextField("" + c.getAge());
            txt.addDataChangeListener((int type, int index) -> {
                mb.setTextLine2(txt.getText());
                c.setAge(Integer.parseInt(txt.getText()));
            });
            txt.setConstraint(TextField.NUMERIC);
            return txt;
        }
        
        private Button createBtnWaze() {

            Image img =
                FontImage.createMaterial(
                    FontImage.MATERIAL_NAVIGATION,
                    UIManager.getInstance().getComponentStyle("Button")
                );            
            
            Button btn = new Button("Waze");
            btn.setIcon(img);
            btn.addActionListener(
                (ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    //url = "waze://?ll=-23.536299,-46.686158&navigate=yes";
                    String url =
                       new StringBuilder()
                       .append("waze://?ll=")
                       .append(c.getGeoLocation())
                       .append("&navigate=yes")
                       .toString();
                    
                    Display.getInstance().execute(url);
                    
            });
            return btn;
        }
                
        private Button createBtnUpdate() {
            Image img =
                FontImage.createMaterial(
                    FontImage.MATERIAL_SAVE,
                    UIManager.getInstance().getComponentStyle("Button")
                );            
            Button btn = new Button("Salvar",img);
            btn.setUIID("Button");
            btn.addActionListener(
                (ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    p.updateClient(getClient());
            });
            return btn;
        }

        private Button createBtnDelete() {
            
            Image img =
                FontImage.createMaterial(
                    FontImage.MATERIAL_DELETE,
                    UIManager.getInstance().getComponentStyle("Button")
                );            
            
            Button btn = new Button("Deletar",img);
            btn.addActionListener(
                (ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    p.removeClient(getClient());
                    populateScreen(cCenter);
            });
            return btn;
        }
        
        
    }
    
    
    
}
