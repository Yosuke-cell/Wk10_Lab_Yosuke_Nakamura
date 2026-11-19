package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.sql.Date;
import java.sql.SQLException;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15));
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		
		
		Label title = new Label("Employment Application");
		title.setStyle(
			    "-fx-background-color: black;" +
			    "-fx-text-fill: white;" +
			    "-fx-font-size: 24px;" +
			    "-fx-font-weight: bold;"
			);
		
		Label headerperson = new Label("Perosnal Information");
		headerperson.setStyle(
			    "-fx-background-color: black;" +
			    "-fx-text-fill: white;"
			);
		Label headereligibility = new Label("Employment Eligibility");
		headereligibility.setStyle(
			    "-fx-background-color: black;" +
			    "-fx-text-fill: white;"
			);
		// personal info
		TextField nameField = new TextField();
		TextField addressField = new TextField();
		TextField phoneField = new TextField();
		TextField emailField = new TextField();
		TextField educationField = new TextField();
		
		ToggleGroup gendergroup = new ToggleGroup();
		RadioButton male = new RadioButton("Male");
		RadioButton female = new RadioButton("Female");
		RadioButton other = new RadioButton("Other");
		male.setToggleGroup(gendergroup);
		female.setToggleGroup(gendergroup);
		other.setToggleGroup(gendergroup);

		//Employment eligibility
		DatePicker datepick = new DatePicker();
		TextField positionFeild = new TextField();
		TextField salaryFeild = new TextField();
		
		ToggleGroup legalgroup = new ToggleGroup();
		RadioButton legalyes = new RadioButton("Yes");
		RadioButton legalno = new RadioButton("No");
		legalyes.setToggleGroup(legalgroup);
		legalno.setToggleGroup(legalgroup);

		ToggleGroup relativegroup = new ToggleGroup();
		RadioButton relativeyes = new RadioButton("Yes");
		RadioButton relativeno = new RadioButton("No");
		relativeyes.setToggleGroup(relativegroup);
		relativeno.setToggleGroup(relativegroup);
		
		TextField detailField = new TextField();
		
		Button submit = new Button("Submit");
		
		//layout
		int row = 0;
		
		grid.add(title, 0, row++);
		//personal info
		grid.add(headerperson, 0, row++);
		
		grid.add(new Label("Full Name"), 0, row);
		grid.add(nameField, 1, row++);
		
		grid.add(new Label("Currrent Address"), 0, row);
		grid.add(addressField, 1, row++);
		
		grid.add(new Label("Contact Number"), 0, row);
		grid.add(phoneField, 1, row++);
		
		grid.add(new Label("Email Address"), 0, row);
		grid.add(emailField, 1, row++);
		
		grid.add(new Label("Highest Educational Attainment"), 0, row);
		grid.add(educationField, 1, row++);
		
		grid.add(new Label("Gender"), 0, row);
		grid.add(new HBox(10, male, female, other), 1, row++);
		
		//employment eligibility
		grid.add(headereligibility, 0, row++);
		
		grid.add(new Label("Date Avaliable"), 0, row);
		grid.add(datepick, 1, row++);
		
		grid.add(new Label("Desired Position"), 0, row);
		grid.add(positionFeild, 1, row++);
		
		grid.add(new Label("Desired Salary"), 0, row);
		grid.add(salaryFeild, 1, row++);
		
		grid.add(new Label("Are you legally authorized to work with in the country?"), 0, row);
		grid.add(new HBox(10, legalyes, legalno), 1, row++);
		
		grid.add(new Label("Do you have relatives working for our company?"), 0, row);
		grid.add(new HBox(10, relativeyes, relativeno), 1, row++);
		
		grid.add(new Label("If yes, please explain further"), 0, row);
		grid.add(detailField, 1, row++);
		
		grid.add(submit, 1, row);
		
		//submit button
		submit.setOnAction(e -> {
			if(nameField.getText().isEmpty() || addressField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty() || legalgroup.getSelectedToggle() == null || relativegroup.getSelectedToggle() == null) {
				showAlert(Alert.AlertType.WARNING,
						"Please fill in name, address, phone, email, and answer both Yes or No quesrions.");
				return;
			}
			
		//salary
			double salary = 0;
			if (!salaryFeild.getText().isBlank()) {
			    try {
			        salary = Double.parseDouble(salaryFeild.getText().trim());
			    } catch (NumberFormatException ex) {
			        showAlert(Alert.AlertType.WARNING, "Desired Salary must be a number.");
			        return;
			    }
			    if (salary < 0) {
			        showAlert(Alert.AlertType.WARNING, "Desired Salary cannot be negative.");
			        return;
			    }
			}
			
		RadioButton selectedGender = (RadioButton) gendergroup.getSelectedToggle();
		String gender = (selectedGender == null) ? null : selectedGender.getText();
		Date date = (datepick.getValue() == null) ? null : Date.valueOf(datepick.getValue());
		
		EmploymentForm form = new EmploymentForm(
		        nameField.getText().trim(),
		        addressField.getText().trim(),
		        phoneField.getText().trim(),
		        emailField.getText().trim(),
		        educationField.getText().trim(),
		        gender,
		        date,
		        positionFeild.getText().trim(),
		        salary,
		        legalyes.isSelected(),
		        relativeyes.isSelected(),
		        detailField.getText().trim()
		);
		try {
		    EmploymentDAO dao = new EmploymentDAO();
		    dao.saveEmploymentFor(form);
		    showAlert(Alert.AlertType.INFORMATION, "Application saved!");
		} catch (SQLException ex) {
		    showAlert(Alert.AlertType.ERROR, "Database error: " + ex.getMessage());
		}
		
		});
	
		primaryStage.setTitle("Employment Application");
		primaryStage.setScene(new Scene(grid, 600, 650));
		primaryStage.show();
	}
	
	private void showAlert(AlertType warning, String string) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(warning);
		alert.setHeaderText(null);
		alert.setContentText(string);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
