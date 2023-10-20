package org.todeschini.tarefas.ui.component;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.todeschini.tarefas.TarefasApplication;

import java.io.IOException;

public class SistemaTarefaFX extends Application {

    // contexto do Spring
    private ConfigurableApplicationContext ctx;

//    public static void main(String[] args) {
//        launch(args);
//    }


    @Override
    public void init() throws Exception {
        // cria um contexto do Spring com a classe Application que tem o metodo main
        ctx = new SpringApplicationBuilder(TarefasApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        // carrega em memoria a UI
        FXMLLoader loader = new FXMLLoader(TarefasApplication.class.getResource("/templates/index.fxml"));
        // carrega todos os objetos de spring para dentro do javafx
        loader.setControllerFactory(ctx::getBean);

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() throws Exception {
        // encerra qualquer conection com a base de dados
        ctx.close();

        // Encerra a applicacao
        Platform.exit();
    }
}
