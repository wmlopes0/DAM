using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraFollow : MonoBehaviour
{
    public Transform player;
    public float separationX = 6.0f;
    public float separationY = 0.5f;
    public float smoothSpeed = 0.125f; // Sirve para controlar la suavidad
    public Vector3 offset;

    void FixedUpdate() //FixedUpdate para un movimiento más suave
    {
        Vector3 desiredPosition = player.position + new Vector3(separationX, separationY, offset.z);
        Vector3 smoothedPosition = Vector3.Lerp(transform.position, desiredPosition, smoothSpeed);
        // Actualizamos la posición en Y utilizando separationY para mantener la distancia vertical deseada
        transform.position = new Vector3(smoothedPosition.x, smoothedPosition.y, transform.position.z);

        // Limito el seguimiento en Y solo cuando el jugador sube
        if (player.position.y + separationY < transform.position.y)
        {
            transform.position = new Vector3(transform.position.x, transform.position.y, transform.position.z);
        }
        else
        {
            transform.position = new Vector3(transform.position.x, smoothedPosition.y, transform.position.z);
        }
    }
}
