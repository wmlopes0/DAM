using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Item : MonoBehaviour
{
    public AudioClip itemSound;

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Player")
        {
            GameManager.sharedInstance.CollectCoin();
            AudioSource.PlayClipAtPoint(itemSound, gameObject.transform.position, 1f);
            Destroy(gameObject);
        }
    }
}
