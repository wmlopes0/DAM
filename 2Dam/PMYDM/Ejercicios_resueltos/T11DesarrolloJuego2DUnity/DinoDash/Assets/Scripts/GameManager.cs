using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum GameState
{
    menu,
    inTheGame,
    gameOver
}

public class GameManager : MonoBehaviour
{
    //shareInstance será única y compartida con toda la escena de Unity
    public static GameManager sharedInstance;

    //Declaramos currentGameState del tipo enumerado GameState y lo iniciamos al valor menu
    public GameState currentGameState = GameState.menu;

    //Para habilitar/dehabilitar Canvas
    public Canvas menuCanvas;
    public Canvas gameCanvas;
    public Canvas gameOverCanvas;

    //Coins
    public int collectedCoins = 0;

    private void Awake()
    {
        if (sharedInstance != null && sharedInstance != this)
        {
            Destroy(this);
        }
        else
        {
            sharedInstance = this;
            DontDestroyOnLoad(gameObject);//En este proyecto no nos hará falta
        }
    }

    void Start()
    {
        // Ocultar el Dino y el LevelGenerator
        PlayerController.sharedInstance.gameObject.SetActive(false);
        LevelGenerator.sharedInstance.gameObject.SetActive(false);
        //El juego inicia en este estado
        currentGameState = GameState.menu;
        gameCanvas.enabled = false;
        gameOverCanvas.enabled = false;
        //Sonidos
        menuCanvas.GetComponent<AudioSource>().Play();
        gameCanvas.GetComponent<AudioSource>().Stop();
        gameOverCanvas.GetComponent<AudioSource>().Stop();

    }

    public void StartGame()//Se llama para iniciar la partida
    {
        StartCoroutine(StartGameCoroutine());
    }

    private IEnumerator StartGameCoroutine()
    {
        // Espera un poco antes de iniciar el juego
        yield return new WaitForSeconds(0.5f); // Espera de 0.5 segundos
        collectedCoins = 0;//Reseteo el contador de monedas
        PlayerController.sharedInstance.StartGame();
        LevelGenerator.sharedInstance.GenerateInitialBlocks();
        UpdateGameCanvas.sharedInstance.SetRecordPoints();
        ChangeGameState(GameState.inTheGame);
    }


    public void GameOver()//Se llama cuando el jugador muere
    {
        ChangeGameState(GameState.gameOver);
        LevelGenerator.sharedInstance.RemoveAllTheBlocks();
        UpdateGameOverCanvas.sharedInstance.SetScorePointsAndCoins();
    }

    public void BackToMainMenu() //Lo llamamos cuando el jugador decide finalizar y volver a menú principal
    {
        ChangeGameState(GameState.menu);
    }

    void ChangeGameState(GameState newGameState)
    {
        if (newGameState == GameState.menu)
        {
            // Ocultar el jugador
            PlayerController.sharedInstance.gameObject.SetActive(false);
            // Ocultar el LevelGenerator
            LevelGenerator.sharedInstance.gameObject.SetActive(false);
            //Mostrar menú principal
            currentGameState = GameState.menu;
            menuCanvas.enabled = true;
            gameCanvas.enabled = false;
            gameOverCanvas.enabled = false;
            //Sonidos
            menuCanvas.GetComponent<AudioSource>().Play();
            gameCanvas.GetComponent<AudioSource>().Stop();
            gameOverCanvas.GetComponent<AudioSource>().Stop();
        }

        else if (newGameState == GameState.inTheGame)
        {
            //Muestro el LevelGenerator
            LevelGenerator.sharedInstance.gameObject.SetActive(true);
            //Muestro el jugador
            PlayerController.sharedInstance.gameObject.SetActive(true);
            //Mostrar Dino en acción
            currentGameState = GameState.inTheGame;
            //Oculto/Muestro canvas
            menuCanvas.enabled = false;
            gameCanvas.enabled = true;
            gameOverCanvas.enabled = false;
            //Sonidos
            menuCanvas.GetComponent<AudioSource>().Stop();
            gameCanvas.GetComponent<AudioSource>().Play();
            gameOverCanvas.GetComponent<AudioSource>().Stop();
        }
        else if (newGameState == GameState.gameOver)
        {
            //Mostrar menú GameOver
            currentGameState = GameState.gameOver;
            //Oculto/muestro canvas
            menuCanvas.enabled = false;
            gameCanvas.enabled = false;
            gameOverCanvas.enabled = true;
            //Sonidos
            menuCanvas.GetComponent<AudioSource>().Stop();
            gameCanvas.GetComponent<AudioSource>().Stop();
            gameOverCanvas.GetComponent<AudioSource>().Play();
            // Ocultar el jugador
            PlayerController.sharedInstance.gameObject.SetActive(false);
            //Ocultar el LevelGenerator
            LevelGenerator.sharedInstance.gameObject.SetActive(false);
        }

    }

    public void CollectCoin()
    {
        collectedCoins++;
        UpdateGameCanvas.sharedInstance.SetCoinsNumber();
    }
}
