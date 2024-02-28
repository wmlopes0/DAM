using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;


public class UpdateGameOverCanvas : MonoBehaviour
{
    public static UpdateGameOverCanvas sharedInstance;

    [SerializeField] private TextMeshProUGUI coinsNumber;
    [SerializeField] private TextMeshProUGUI scorePoints;

    private void Awake()
    {
        sharedInstance = this;
    }

    public void SetScorePointsAndCoins()
    {
        scorePoints.text = PlayerController.sharedInstance.GetDistanceTravelled().ToString("f0");
        coinsNumber.text = GameManager.sharedInstance.collectedCoins.ToString();
    }
}
