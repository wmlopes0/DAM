using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelGenerator : MonoBehaviour
{

    //Instancia compartida de mi clase (un solo generador de niveles)
    public static LevelGenerator sharedInstance;

    //Lista con los bloques que tenemos generados en el juego
    public List<LevelBlock> currentLevelBlocks = new List<LevelBlock>();

    //Lista con los bloques disponibles para ir generándolos en el juego de forma aleatoria
    public List<LevelBlock> allTheLevelBlocks = new List<LevelBlock>();

    //Punto de inicio del nivel donde empezará a crearse el primer bloque de todos
    public Transform levelInitialPoint;

    private void Awake()
    {
        sharedInstance = this;
    }

    private void Start()
    {
        GenerateInitialBlocks();
    }

    public void GenerateInitialBlocks()
    {
        for (int i = 0; i < 3; i++)
        {
            AddNewBlock();
        }
    }

    public void AddNewBlock()
    {
        //Creamos un número aleatorio para posteriormente seleccionar
        //un bloque aleatorio entre los que tenemos disponibles
        int randomIndex = Random.Range(0, allTheLevelBlocks.Count);

        //Instanciamos el bloque aleatorio en el objeto "block"
        //Instantiate sirve para crear una copia de un objeto concretamente uno
        //de los bloques prefab para construir el nivel
        LevelBlock block = (LevelBlock)Instantiate(allTheLevelBlocks[randomIndex]);

        //Para mantener la jerarquía ordenada lo colocamos como hijo
        block.transform.SetParent(this.transform, false);

        //Inicializamos una posición para el bloque que hemos creado
        Vector3 blockPosition = Vector3.zero;

        //Comprobamos si es el primer bloque o si ya hay bloques en la escena
        if (currentLevelBlocks.Count == 0)
        {
            //Colocamos el primer bloque en el juego
            blockPosition = levelInitialPoint.position;
        }
        else
        {
            //Hay bloques en el juego así que lo colocamos en la posición
            //marcada por el último bloque en pantalla
            blockPosition = currentLevelBlocks[currentLevelBlocks.Count - 1].exitPoint.position;
        }

        //Le asigno la posición resultante del if
        block.transform.position = blockPosition;

        //Ya está en pantalla así que lo meto en la lista
        currentLevelBlocks.Add(block);
    }

    public void RemoveOldBlock()
    {
        LevelBlock block = currentLevelBlocks[0]; //Escojo el más antiguo de la lista
        currentLevelBlocks.Remove(block); //Lo borro de la lista
        Destroy(block.gameObject); //Destruyo su gameObject para que desaparezca del juego
    }

    public void RemoveAllTheBlocks()
    {
        while (currentLevelBlocks.Count > 0)
        {
            RemoveOldBlock();
        }
    }

}
