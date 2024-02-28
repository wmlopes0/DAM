using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class UpdateGameCanvas : MonoBehaviour
{

    public static UpdateGameCanvas sharedInstance;

    [SerializeField] private TextMeshProUGUI coinsNumber;
    [SerializeField] private TextMeshProUGUI scorePoints;
    [SerializeField] private TextMeshProUGUI recordPoints;

    private void Awake()
    {
        sharedInstance = this;
    }

    public void SetRecordPoints()
    {
        recordPoints.text = PlayerPrefs.GetFloat("highscore", 0).ToString("f0");
    }

    public void SetCoinsNumber()
    {
        coinsNumber.text = GameManager.sharedInstance.collectedCoins.ToString();
    }

    void Update()
    {
        if (GameManager.sharedInstance.currentGameState == GameState.inTheGame)
        {
            //Formateo la salida para que me salgan 0 decimales
            scorePoints.text = PlayerController.sharedInstance.GetDistanceTravelled().ToString("f0");
            //Comprobamos si hemos rebasado el record de distancia, si esto pasa durante el juego este se actualizará junto a nuestra puntuación
            if (PlayerPrefs.GetFloat("highscore", 0) < PlayerController.sharedInstance.distanceTravelled)
            {
                recordPoints.text = PlayerController.sharedInstance.distanceTravelled.ToString("f0");
            }
        }
    }
}
