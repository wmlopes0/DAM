using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KillTrigger : MonoBehaviour
{
    public AudioClip hit;

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Player"))
        {
            AudioSource.PlayClipAtPoint(hit, gameObject.transform.position, 1f);
            PlayerController.sharedInstance.KillPlayer();
        }
    }
}
