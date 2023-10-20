package org.todeschini.tarefas;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.todeschini.tarefas.ui.component.SistemaTarefaFX;

@SpringBootApplication
public class TarefasApplication {

	public static void main(String[] args) {
		// comentado pois esta nao é uma applicacao web
		// SpringApplication.run(TarefasApplication.class, args);
		// integrando Application fx com spring
		Application.launch(SistemaTarefaFX.class, args);
	}

}
