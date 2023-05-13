package Controller;

import Model.MyModel;
import View.MyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyController {
    private MyModel model;
    private MyView view;

    public MyController(MyModel model, MyView view) {
        this.model = model;
        this.view = view;

        // Add an action listener to the button
        view.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the model with a new message
                model.setMessage("Hello, World!");

                // Update the view with the new message
                view.setMessage(model.getMessage());
            }
        });
    }
}
