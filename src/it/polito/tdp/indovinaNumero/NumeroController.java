package it.polito.tdp.indovinaNumero;




import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NumeroController {
	private boolean inGame;
	private int segreto;
	private int difficolta;
	private int numero;
	private int maxTentativi;
	private int numTentativi;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStartStop;

    @FXML
    private Label labelTentativo;

    @FXML
    private ProgressBar pbTentativo;

    @FXML
    private Label labelRisultato;

    @FXML
    private ComboBox<Integer> boxDifficolta;

    @FXML
    private Button btnProva;

    @FXML
    private TextField textNumero;

    @FXML
    void doStartStop(ActionEvent event) {
    	if(inGame){// se clicca sul bottone abandona
    		//abandonna
    		this.btnStartStop.setText("inizia");//cambio il testo del bottone
    		this.btnProva.setDisable(true);// disabilita il bottone per fare la prova
        	this.boxDifficolta.setDisable(false);// abilita la combobox della difficolta
        	
        	 this.labelRisultato.setTextFill(Color.RED);// cambia il testo in rosso per mostrare 
        	this.labelRisultato.setText("Hai Abbandonato");// cambio il testo per notificare l'abandonno del gioco
        	this.labelTentativo.setText(String.format("Il numero segreto era %d", segreto));// mostra il numero segreto al giocatore che occoreva indovinare
 
        	this.textNumero.setDisable(true);//disabilita la textfield d'inserimento dei numeri
    		
    		inGame=false;
    	}
    	else{
    		
    		this.textNumero.clear();
    	if(this.boxDifficolta.getValue()==null){
    		this.labelTentativo.setText("Scegliere una difficolta");
    		return;
    	}
    	this.pbTentativo.setProgress(0.0);//azzero la progressbar
    	this.difficolta=this.boxDifficolta.getValue();//
    	this.numTentativi=0;
    	this.maxTentativi=(int)(Math.log(difficolta)/Math.log(2.0)+1);
    	segreto =(int) (Math.random()*difficolta+1);
    	this.btnStartStop.setText("Abandonna");
    	this.labelRisultato.setDisable(false);
    	 this.labelRisultato.setTextFill(Color.GREEN);
    	this.labelRisultato.setText("Start");
    	this.labelTentativo.setDisable(false);
    	this.textNumero.setDisable(false);
    	this.btnProva.setDisable(false);
    	this.boxDifficolta.setDisable(true);
    	this.labelTentativo.setText(String.format("Tentativi: %d/%d ",numTentativi, this.maxTentativi));
    		inGame=true;
    	}
    	System.out.println(segreto);

    }
    
    
    
    
    

    private void startController(){
    	
    	
  
    		//abandonna
    		
    		this.btnStartStop.setText("inizia");//cambio il testo del bottone
    		this.btnProva.setDisable(true);// disabilita il bottone per fare la prova
    		this.boxDifficolta.setDisable(false);// abilita la combobox della difficolta
    		this.textNumero.setDisable(true);//disabilita la textfield d'inserimento dei numeri
    		inGame=false;
    		
    	
    	
    }
    
    
 
    
    
    
    
    @FXML
    void doProva(ActionEvent event) {
    	
   String num = this.textNumero.getText();
   
   try{
	  this.numero = Integer.parseInt(num);
	   
   }catch(RuntimeException re){
	   this.labelTentativo.setText("Inserire un numero intero");
	   return;
	   
   }
   this.numTentativi++;
   this.pbTentativo.setProgress((double)numTentativi/this.maxTentativi);
   this.labelTentativo.setText(String.format("Tentativi: %d/%d",numTentativi, this.maxTentativi));
   
   
  if(this.numTentativi<this.maxTentativi){
  if(numero<segreto){
		   this.labelRisultato.setTextFill(Color.RED); 
       	this.labelRisultato.setText("inferiore al numero segreto");
       
       	
		   
	   }
	   
	   else if(numero>segreto){
		   this.labelRisultato.setTextFill(Color.RED); 
	       	this.labelRisultato.setText("superiore al numero segreto");
	       
	   }
   
	   else if(numero==segreto){
		   this.labelRisultato.setTextFill(Color.GREEN); 
	      	this.labelRisultato.setText("Hai Trovato il numero segreto");
	    	//abandonna
			this.startController();
	
	
	   }
  }
	   else  {//verifico se l'ultimo numero entrato è giusto se non scatto l'errore max tentativi raggiunti
		   if(numero==segreto){
			   this.labelRisultato.setTextFill(Color.GREEN); 
		      	this.labelRisultato.setText("Hai Trovato il numero segreto");
		    	//abandonna
				this.startController();
		
		
		   }
		   else{
		   this.labelRisultato.setTextFill(Color.RED); 
		 	this.labelRisultato.setText("Max Tentativi raggiunti");
		    this.labelTentativo.setText(String.format("Il numero segreto era %d", segreto));
			//abandonna
		    this.startController();
			
		   }
		
	   }
	   
    	
   }	
 
 

   
    @FXML
    void initialize() {
        assert btnStartStop != null : "fx:id=\"btnStartStop\" was not injected: check your FXML file 'numero.fxml'.";
        assert labelTentativo != null : "fx:id=\"labelTentativo\" was not injected: check your FXML file 'numero.fxml'.";
        assert pbTentativo != null : "fx:id=\"pbTentativo\" was not injected: check your FXML file 'numero.fxml'.";
        assert labelRisultato != null : "fx:id=\"labelRisultato\" was not injected: check your FXML file 'numero.fxml'.";
        assert boxDifficolta != null : "fx:id=\"boxDifficolta\" was not injected: check your FXML file 'numero.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'numero.fxml'.";
        assert textNumero != null : "fx:id=\"textNumero\" was not injected: check your FXML file 'numero.fxml'.";
     this.boxDifficolta.getItems().addAll(10, 100,1000);
     this.inGame=false;
     
		   this.labelRisultato.setTextFill(Color.GREEN); 
	      	this.labelRisultato.setText("Benvenuto(a)");
	    	
	
	
	  
     }
}
