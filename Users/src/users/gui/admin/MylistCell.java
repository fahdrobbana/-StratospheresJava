
package users.gui.admin;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

  class MyListCell extends ListCell<MyData> {
        private AnchorPane myAnchorPane;
        private ImageView imageView;
        private Label nameLabel;
        private Label lastnameLabel;
        private Label emailLabel;

        public MyListCell() {
            super();
            myAnchorPane = new AnchorPane();
            imageView = new ImageView();
            nameLabel = new Label();
            lastnameLabel = new Label();
            emailLabel = new Label();

            myAnchorPane.getChildren().addAll(imageView, nameLabel, lastnameLabel,emailLabel);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50) ;
            AnchorPane.setLeftAnchor(imageView, 10.0);
            AnchorPane.setTopAnchor(imageView, 10.0);

            AnchorPane.setLeftAnchor(nameLabel, 70.0);
            AnchorPane.setTopAnchor(nameLabel, 20.0);
            
             AnchorPane.setLeftAnchor(lastnameLabel, 120.0);
            AnchorPane.setTopAnchor(lastnameLabel, 20.0);
            
             AnchorPane.setLeftAnchor(emailLabel, 190.0);
            AnchorPane.setTopAnchor(emailLabel, 20.0);
            
          
        }
        
        @Override
        protected void updateItem(MyData item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                imageView.setImage(new Image(item.getImage()));
                nameLabel.setText(item.getName());
                lastnameLabel.setText(item.getLastname());
                emailLabel.setText(item.getEmail());
                setGraphic(myAnchorPane);
            }
        }
  
}
