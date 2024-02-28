using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class PlayerController : MonoBehaviour
{
    //Singleton
    public static PlayerController sharedInstance;

    [SerializeField] private float jumpForce = 6.0f; //Fuerza de salto para multiplicarse a Vector2.up
    [SerializeField] private LayerMask groundLayerMask; //Seleccionaremos en el inspector la capa deseado
    [SerializeField] private float runningSpeed = 6.0f;

    private Rigidbody2D _rigidbody2d; //Para guardar la referencia al componente Rigidbody2D del Player
    private const float _distanceRaycast = 2.0f; //Longitud del Raycast
    private Animator _animatorPlayer; //Para guardar la referencia al componente Animator del Player
    private Vector3 startPosition;


    private readonly int _animIdIsAlive = Animator.StringToHash("isAlive");
    private readonly int _animIdIsRunning = Animator.StringToHash("isRunning");
    private readonly int _animIdIsGrounded = Animator.StringToHash("isGrounded");

    public float distanceTravelled = 0000;

    private bool hasStarted = false; //Variable para controlar que no empiece a correr hasta que el usuario lo diga



    private void Awake()
    {
        sharedInstance = this;
        startPosition = this.transform.position;
        _rigidbody2d = GetComponent<Rigidbody2D>();
        _animatorPlayer = GetComponent<Animator>();
        _animatorPlayer.SetBool(_animIdIsAlive, true);
    }


    public void StartGame()
    {
        _animatorPlayer.SetBool(_animIdIsAlive, true);
        this.transform.position = startPosition;
    }

    void Update()
    {
        // Solo salta si estamos en el estado inTheGame y el jugador ha iniciado
        if (GameManager.sharedInstance.currentGameState == GameState.inTheGame)
        {
            if (!hasStarted)
            {
                // Espera a que el jugador presione el click izquierdo del ratón para empezar a correr
                if (Input.GetButtonDown("Fire1"))
                {
                    hasStarted = true; // El jugador ha iniciado
                    _animatorPlayer.SetBool(_animIdIsRunning, true); // Ahora el jugador empieza a correr
                }
            }
            else
            {
                _animatorPlayer.SetBool(_animIdIsGrounded, isOnTheFloor());
                if (Input.GetButtonDown("Fire1"))
                {
                    if (isOnTheFloor())
                    {
                        GetComponent<AudioSource>().Play();
                        Jump();
                    }
                }
            }
        }
    }


    private void FixedUpdate()
    {
        //Solo corre si estamos en el estado inTheGame
        if (GameManager.sharedInstance.currentGameState == GameState.inTheGame && hasStarted)
        {
            if (_rigidbody2d.velocity.x < runningSpeed)
            {
                _rigidbody2d.velocity = new Vector2(runningSpeed, _rigidbody2d.velocity.y);
            }
        }
    }

    public bool isOnTheFloor()
    {
        bool isOnTheFloor = false;

        if (Physics2D.Raycast(this.transform.position, Vector2.down, _distanceRaycast, groundLayerMask))
        {
            isOnTheFloor = true;
        }
        return isOnTheFloor;
    }

    public void Jump()
    {
        _rigidbody2d.AddForce(Vector2.up * jumpForce, ForceMode2D.Impulse);
    }

    public void KillPlayer()
    {
        hasStarted = false;// Si el personaje muere reseteo la variable hasStarted
        _animatorPlayer.SetBool(_animIdIsAlive, false);
        Invoke("DelayGameOver", 1f);
        //Comprobamos si hemos hecho record de distancia
        if (PlayerPrefs.GetFloat("highscore", 0) < distanceTravelled)
        {
            PlayerPrefs.SetFloat("highscore", distanceTravelled);
        }
    }

    public void DelayGameOver()
    {
        _rigidbody2d.Sleep();
        GameManager.sharedInstance.GameOver();
    }

    public float GetDistanceTravelled()
    {
        distanceTravelled = Vector2.Distance(new Vector2(startPosition.x, 0), new Vector2(this.transform.position.x, 0));
        return distanceTravelled;
    }
}
