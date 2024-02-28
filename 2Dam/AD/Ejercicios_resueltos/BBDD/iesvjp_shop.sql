-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-01-2024 a las 12:26:27
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `iesvjp_shop`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(512) NOT NULL,
  `destacada` tinyint(1) NOT NULL,
  `imagen` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `destacada`, `imagen`) VALUES
(1, 'Portátiles', 1, 'https://img.pccomponentes.com/articles/1079/10792741/1255-acer-aspire-3-a315-58-587e-intel-core-i5-1135g7-16gb-512gb-ssd-156.jpg'),
(2, 'Componentes', 0, 'https://img.pccomponentes.com/articles/1066/10661780/1223-corsair-icue-h150i-elite-capellix-xt-kit-de-refrigeracion-liquida-360mm-negro.jpg'),
(3, 'Monitores', 1, 'https://thumb.pccomponentes.com/w-300-300/articles/1065/10658701/1132-lg-ultragear-24gq50f-b-24-led-fullhd-165hz-freesync-premium.jpg'),
(4, 'SmartPhones', 0, 'https://img.pccomponentes.com/articles/1077/10777884/1943-apple-iphone-15-pro-max-256gb-titanio-blanco-libre.jpg'),
(5, 'Televisores', 0, 'https://img.pccomponentes.com/articles/1024/10241277/1484-lg-65nano766qa-65-led-nanocell-ultrahd-4k-hdr10-pro-foto.jpg'),
(6, 'Hogar', 1, 'https://img.pccomponentes.com/pcblog/1650405600000/gran-electrodomestico-1-1.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(512) NOT NULL,
  `descripcion` varchar(512) NOT NULL,
  `pvp` float NOT NULL,
  `descuento` float DEFAULT NULL,
  `imagen` varchar(512) DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `pvp`, `descuento`, `imagen`, `categoria_id`) VALUES
(1, 'Acer Aspire 3 A315-58-587E Intel Core i5-1135G7/16GB/512GB SSD/15.6\"', 'Ya estés en casa, en clase o en el trabajo, consigue todo el rendimiento que necesitas con el procesador Intel® Core™ de 11.ª generación de Acer Aspire 3, que mantiene el orden y logra que tus aplicaciones funcionen de forma constante y sin problemas.', 429, 0, 'https://img.pccomponentes.com/articles/1079/10792741/1255-acer-aspire-3-a315-58-587e-intel-core-i5-1135g7-16gb-512gb-ssd-156.jpg', 1),
(2, 'ASUS VivoBook 16 F1605PA-MB104 Intel Core i5-11300H/8GB/512GB SSD/16\"', 'Todo es más fluido con la potencia del portátil ASUS Vivobook 16, el portátil repleto de funciones fáciles de usar que incluye una bisagra plana de 180°, un protector de cámara web físico y teclas de función dedicadas para encender o apagar el micrófono.', 429, 0.28, 'https://img.pccomponentes.com/articles/1073/10734095/1125-asus-vivobook-16-f1605pa-mb104-intel-core-i5-11300h-8gb-512gb-ssd-16-1b04bd66-0ece-4bb5-9a38-98606c618b7b.jpg', 1),
(3, 'HP Victus 15-fa0004ns Intel Core i5-12500H/16GB/512GB SSD/GTX 1650/15.6\"', 'La potencia de un sobremesa en un diseño portátil. El portátil HP Victus se ha diseñado para jugar en máxima calidad. Este elegante dispositivo cuenta con un procesador Intel® Core™ de 12.ª generación y una tarjeta gráfica moderna. El diseño de este portátil es tan increíble como su hardware, con distintas opciones de color, refrigeración mejorada, un teclado gaming multifunción completo y una cámara HD con reducción temporal de ruidos.', 699, 0.22, 'https://img.pccomponentes.com/articles/1057/10579900/1845-hp-victus-15-fa0004ns-intel-core-i5-12500h-16gb-512gb-ssd-gtx-1650-156.jpg', 1),
(4, 'Alurin Go Start Intel Celeron N4020/8GB/256GB SSD/14\"', '¿Estás buscando un portátil rápido y eficiente que puedas llevar contigo a cualquier parte? Nuestro Alurin Go Start N4020 es la elección perfecta para ti. Podrás realizar tus tareas diarias con facilidad y sin retrasos.\r\nAdemás, su pantalla de alta definición te permitirá disfrutar de tus películas, series y videos favoritos con una calidad de imagen excepcional.', 299, 0, 'https://img.pccomponentes.com/articles/1066/10663373/1374-alurin-go-start-intel-pentium-n4020-8gb-256gb-ssd-14-comprar.jpg', 1),
(5, 'HP 15-fc0064ns AMD Ryzen 5 7520U/16GB/512GB SSD/15.6\"', 'Este portátil HP de 15,6 pulgadas cuenta con muchas características orientadas a la productividad y a velar por tu cartera y por el planeta. Este potente portátil está construido con materiales reciclados y cuenta con un procesador AMD , mucho espacio de almacenamiento y gráficos fascinantes. Además, su proporción pantalla-cuerpo del 85 % y microbordes en 3 lados ofrecen espacio más que suficiente para ver y hacer más.', 469, 0, 'https://img.pccomponentes.com/articles/1073/10733246/1625-hp-15-fc0064ns-amd-ryzen-5-7520u-16gb-512gb-ssd-156.jpg', 1),
(6, 'PcCom Revolt 4060 Intel Core i7-13700H/16GB/500GB SSD/RTX 4060/15.6\" + Windows 11 Home', 'El portátil PcCom Revolt 4060 es ideal para trabajar y jugar sin problemas con su potente procesador Intel Core i7-13700H, sus 16GB de RAM y su SSD de 500GB. Además, su tarjeta gráfica NVIDIA RTX 4060 de 8GB GDDR6 proporciona gráficos detallados y realistas en tus juegos y aplicaciones acompañando la pantalla de 15.6\" con una tasa de refresco de 144Hz, ofrece una experiencia visual suave y envolvente. ', 1449, 0.08, 'https://img.pccomponentes.com/articles/1067/10674403/1102-pccom-revolt-4060-intel-core-i7-13700h-16gb-500gb-ssd-rtx-4060-156-windows-11-home-comprar.jpg', 1),
(7, 'ASUS VivoBook F1605PA-MB124W Intel Core i7-11370H/8GB/512GB SSD/16\"', 'Todo es más fluido con la potencia del portátil ASUS Vivobook 16, el portátil repleto de funciones fáciles de usar que incluye una bisagra plana de 180°, un protector de cámara web físico y teclas de función dedicadas para encender o apagar el micrófono.', 999, 0, 'https://img.pccomponentes.com/articles/1072/10726959/1216-asus-vivobook-f1605pa-mb124w-intel-core-i7-11370h-8gb-512gb-ssd-16.jpg', 1),
(8, 'Lenovo IdeaPad Slim 3 15IAH8 Intel Core i5-12450H/16GB/512GB SSD/15.6\'\'', 'Destaque en cualquier lugar con la computadora portátil Lenovo IdeaPad Slim 3, diseñada para ser liviana y delgada, de construcción robusta con resistencia y durabilidad de grado militar para soportar caídas y condiciones de viaje extremas. Trabaje de manera más inteligente y fluida todos los días en múltiples aplicaciones simultáneamente y mientras viaja, con el sólido rendimiento de los últimos procesadores Intel y abundante memoria, mejorada por el rendimiento adaptativo de la tecnología Smart Power.', 599, 0, 'https://img.pccomponentes.com/articles/1079/10797786/1581-lenovo-ideapad-slim-3-15iah8-intel-core-i5-12450h-16gb-512gb-ssd-156.jpg', 1),
(9, 'HP 15-fd0076ns Intel Core i5-1335U/16GB/512GB SSD/15.6\"', 'El portátil HP de 15,6 pulgadas te da el poder para hacer más gracias a la potencia fiable del procesador Intel® , además de un amplio almacenamiento y unos gráficos potentes en un dispositivo elegante y cuidadosamente diseñado. Creado para superar cualquier reto que se te ponga delante y fabricado con plástico destinado a acabar en el océano y plástico reciclado postconsumo , resulta fácil mostrar algo de amor por el planeta.', 599, 0, 'https://img.pccomponentes.com/articles/1073/10733266/1519-hp-15-fd0076ns-intel-core-i5-1335u-16gb-512gb-ssd-156-review.jpg', 1),
(10, 'Acer Nitro V 15 ANV15-51-51PQ Intel Core i5-13420H/16GB/1TB SSD/RTX 3050/15.6\"', 'Más allá del rendimiento. Libera tu destreza gaming con Nitro V 15, equipado con hasta un procesador Intel® Core™ i7 de 13.ª generación. Tanto si libras una batalla como si orquestas un asalto final, el almacenamiento y la RAM DDR5 de máxima velocidad del portátil te garantizan un movimiento a la velocidad de la luz.', 769, 0.1, 'https://img.pccomponentes.com/articles/1079/10792747/1824-acer-nitro-v-15-anv15-51-51pq-intel-core-i5-13420h-16gb-1tb-ssd-rtx-3050-156-review.jpg', 1),
(11, 'Intel Core i5-12400F 2.5 GHz', 'Procesadores Intel® Core™ de 12ª generación: una generación como ninguna otra antes. Con una nueva arquitectura híbrida de rendimiento sin precedentes, los procesadores Intel® Core™ de 12ª generación ofrecen una combinación única de núcleos de rendimiento y eficiencia (núcleo P y núcleo E). Y eso significa gozar de rendimiento en el mundo real, un resultado escalado intuitivamente para adaptarse a cualquier cosa que estés haciendo.', 148, 0, 'https://img.pccomponentes.com/articles/83/834922/1636-intel-core-i5-12400f-44-ghz.jpg', 2),
(12, 'AMD Ryzen 7 7800X3D 4.2 GHz/5 GHz', 'El procesador para juegos que domina el mundo de la mano de la tecnología AMD 3D V-Cache™, para ganar aún más rendimiento de juego. Sin importar la configuración ni la resolución que uses, lleva a tu equipo a la victoria con este maravilloso procesador para juegos. Disfruta, además, las ventajas de AMD 3D V-Cache™, la tecnología de punta que es sinónimo de latencia baja y mucho rendimiento de juego.', 429, 0.23, 'https://img.pccomponentes.com/articles/1066/10665103/1575-amd-ryzen-7-7800x3d-42-ghz-5-ghz.jpg', 2),
(13, 'AMD Ryzen 5 5500 3.6GHz Box', 'Cuando cuentas con la arquitectura de procesadores de escritorio más avanzada del mundo para jugadores y creadores de contenido, las posibilidades son infinitas. Ya sea que juegues los juegos más recientes, diseñes el próximo rascacielos o proceses datos, necesitas un procesador poderoso que pueda dar respuesta a todas estas demandas, y más. Sin lugar a dudas, los procesadores para computadoras de escritorio AMD Ryzen™ serie 5000 elevan el nivel de expectativa para jugadores y artistas por igual.', 99, 0.4, 'https://img.pccomponentes.com/articles/1020/10204431/162-amd-ryzen-5-5500-36ghz-box-mejor-precio.jpg', 2),
(14, 'Zotac Gaming GeForce RTX 4060 Ti TWIN EDGE 16GB GDDR6 DLSS3', 'Con un tamaño de ranura reducido de 2.2, Twin Edge tiene una amplia compatibilidad con carcasas de PC y es una excelente opción para aquellos que desean construir una PC para juegos SFF capaz de ofrecer velocidades de fotogramas y rendimiento fluidos en los últimos lanzamientos de títulos.', 479, 0.14, 'https://img.pccomponentes.com/articles/1079/10793422/1877-zotac-gaming-geforce-rtx-4060-ti-twin-edge-16gb-gddr6-dlss3.jpg', 2),
(15, 'Gigabyte GeForce RTX 4070 Windforce OC 12 GB GDDR6X DLSS3', 'Las GPU NVIDIA® GeForce RTX® serie 40 son más que rápidas para jugadores y creadores. Cuentan con la tecnología de la arquitectura ultra eficiente NVIDIA Ada Lovelace, que ofrece un salto espectacular tanto en rendimiento como en gráficos con tecnología de IA. Disfruta de mundos virtuales realistas con trazado de rayos y juegos con FPS ultra altos y la latencia más baja. Descubre nuevas y revolucionarias formas de crear contenido y una aceleración de flujo de trabajo sin precedentes.', 629, 0, 'https://img.pccomponentes.com/articles/1070/10706592/1556-gigabyte-geforce-rtx-4070-windforce-oc-12gb-gddr6x-dlss3.jpg', 2),
(16, 'Zotac Gaming GeForce RTX 4070Ti Trinity 12GB GDDR6X DLSS3', 'Aprovechando un nuevo diseño inspirado en la aerodinámica, ZOTAC GAMING GeForce RTX 4070 Ti Trinity utiliza la GPU para juegos más avanzada del mundo con tecnología de la arquitectura NVIDIA Ada Lovelace. Utilizando tecnologías de refrigeración de vanguardia derivadas del modelo insignia, Trinity ofrece a los jugadores el FPS vertiginoso que necesitan en los títulos más recientes.', 829, 0.5, 'https://img.pccomponentes.com/articles/1064/10646257/169-zotac-gaming-geforce-rtx-4070ti-trinity-12gb-gddr6x.jpg', 2),
(17, 'Corsair Vengeance RGB DDR5 6000MHz PC5-48000 32GB 2x16GB CL36 Negra', 'La memoria CORSAIR VENGEANCE RGB DDR5 ofrece rendimiento DDR5, frecuencias más altas y mayores capacidades optimizadas para placas base Intel®, mientras ilumina su PC con iluminación RGB de diez zonas dinámica y direccionable individualmente.\r\n\r\nLos chips de memoria de alta frecuencia cuidadosamente seleccionados permiten un procesamiento, renderizado y almacenamiento en búfer más rápidos que nunca, con regulación de voltaje integrada para un overclocking fácil y finamente controlado.', 115, 0, 'https://img.pccomponentes.com/articles/1071/10711396/1323-corsair-vengeance-rgb-ddr5-6000mhz-pc5-48000-32gb-2x16gb-cl36-negro.jpg', 2),
(18, 'Corsair Vengeance LPX DDR4 3200 PC4-25600 16GB 2X8GB CL16 Negro', 'La memoria Corsair Vengeance LPX se ha diseñado para overclocking de alto rendimiento. El disipador de calor, fabricado en aluminio puro, permite una disipación térmica más rápida, la placa impresa de ocho capas administra el calor y proporciona una capacidad superior para incrementar el overclocking.', 41, 0, 'https://img.pccomponentes.com/articles/26/262822/corsair-vengeance-lpx-ddr4-3200-pc4-25600-16gb-2x8gb-cl16-negro.jpg', 2),
(19, 'Corsair iCUE H100x RGB ELITE Kit de Refrigeración Líquida 240mm Negro', 'El refrigerador líquido de CPU CORSAIR iCUE H100x RGB ELITE combina una excelente refrigeración y una estética refinada, con dos ventiladores PWM CORSAIR SP120 RGB ELITE Series e impresionante iluminación RGB.Todo lo que necesita para mantener su procesador funcionando al máximo rendimiento con refrigeración líquida, todo en una sola caja: perfecto para principiantes y ensambladores noveles.', 113, 0, 'https://img.pccomponentes.com/articles/1066/10660339/1608-corsair-icue-h100x-rgb-elite-kit-de-refrigeracion-liquida-240mm-negro.jpg', 2),
(20, 'Tempest Liquid Cooler 240 Kit Refrigeración Líquida Blanco', 'Tempest continúa innovando en su línea de productos gaming y presenta el nuevo Refrigerador Líquido Tempest Liquid Cooler 240 Blanca en un elegante color blanco. Este potente refrigerador líquido está diseñado para proporcionar un rendimiento excepcional de enfriamiento sin las distracciones de la iluminación RGB.Abandona los ventiladores de aire tradicionales y adopta un sistema de enfriamiento líquido avanzado y eficiente..', 59, 0.31, 'https://img.pccomponentes.com/articles/1079/10793235/1317-tempest-liquid-cooler-240-kit-refrigeracion-liquida-blanco.jpg', 2),
(21, 'Samsung ViewFinity S9 LS49A950UIPXEN 49\" QLED UltraWide Dual QuadHD USB-C Curva', 'El Samsung ViewFinity LS49A950UIPXEN es un monitor curvo de 49 pulgadas con una resolución de 5120 x 1440 píxeles. Tiene un ratio de aspecto de 32:9, lo que lo hace ideal para juegos, productividad y entretenimiento. El monitor está equipado con una pantalla QLED, que ofrece colores vivos y precisos.  Pantalla curva de 32:9 con resolución Dual QHD con más espacio para la multitarea y panel con tecnología DisplayHDR 400.', 1005, 0, 'https://img.pccomponentes.com/articles/1076/10763619/1790-samsung-viewfinity-s9-ls49a950uipxen-49-qled-ultrawide-dual-quadhd-usb-c-curva.jpg', 3),
(22, 'Alurin CoreVision 100IPSLite 23.8\" FHD 100Hz Freesync', 'Te presentamos el nuevo monitor Alurin Core Vision 23.8”, con panel IPS y resolución Full HD 1920x1080, diseñado para ofrecer una experiencia visual excepcional. Imágenes nítidas, colores vibrantes y una avanzada tecnología de pantalla sin perder la atención de la ergonomía y comodidad.\r\n\r\nCuando tienes una pantalla que ofrece colores reales y movimientos ágiles, hasta las hojas de cálculo parecen entretenidas.', 89, 0.1, 'https://img.pccomponentes.com/articles/1075/10756386/1454-alurin-corevision-100ipslite-238-fhd-100hz-freesync-comprar.jpg', 3),
(23, 'LG Ultragear 24GQ50F-B 24\" LED FullHD 165Hz FreeSync Premium', 'Cambia la historia con LG UltraGear™, la mejor calidad de imagen y la máxima velocidad de respuesta. LG UltraGear™ es un monitor gaming potente que se adapta a las más altas exigencias de tus videojuegos.\r\n\r\nUna velocidad ultrarrápida de 165Hz permite a los gamers ver el próximo frame rápidamente y hace que la imagen aparezca mucho más suave. Identifica más rápido a tus enemigos y apunta a tu objetivo más fácilmente. ', 139, 0, 'https://img.pccomponentes.com/articles/1065/10658701/1132-lg-ultragear-24gq50f-b-24-led-fullhd-165hz-freesync-premium.jpg', 3),
(24, 'Samsung Essential S24C330GAU 24\" LCD IPS FullHD 100Hz FreeSync', 'El monitor S24C330GAU es un dispositivo de visualización de 24 pulgadas fabricado por Samsung. Ofrece una resolución de pantalla de 1920x1080 (Full HD), lo que proporciona una calidad de imagen nítida y detallada. Este monitor utiliza una tecnología de panel LCD para mostrar colores vibrantes y ángulos de visión amplios. Además, cuenta con opciones de conectividad como puertos HDMI y DP para conectar dispositivos externos como computadoras, consolas de videojuegos y reproductores multimedia.', 124, 0.5, 'https://img.pccomponentes.com/articles/1076/10763772/1740-samsung-essential-s24c330gau-24-lcd-ips-fullhd-100hz-freesync-review.jpg', 3),
(25, 'LG 34WP65CP-B 34\" LED UWQHD 160Hz FreeSync Premium Curvo', 'El LG UltraWide™ 34WP65CP formato 21:9 con 32% más de campo de visión es perfecto, para trabajar desde casa u oficina. Permite ver distintas ventanas y tener abiertos distintos programas al mismo tiempo. Para comprender mejor la visión de los creadores de contenidos, el LG UltraWide Monitor 34WP65CP es compatible con el alto rango dinámico de VESA DisplayHDR™ 400, admitiendo niveles específicos de color y brillo que superan las capacidades de los monitores convencionales.', 349, 0.5, 'https://img.pccomponentes.com/articles/1072/10720496/1904-lg-34wp65cp-b-34-led-uwqhd-160hz-freesync-premium-curvo.jpg', 3),
(26, 'AOC CU34G2X/BK 34\" LED Wide QuadHD 144Hz FreeSync Curvo', 'Monitor de gaming curvo de 34\", 144 Hz, tiempo de respuesta de 1 ms y resolución de pantalla panorámica de 21:9. Con el CU34G2X vivirás una experiencia de juego increíble. Gracias a la resolución WQHD, este monitor curvo de 34\" ofrece imágenes increíblemente detalladas y nítidas. Además, su tasa de refresco de 144 Hz, su tiempo de respuesta de 1 ms y AMD FreeSync ofrece una fluidez de juego extraordinaria.', 414, 0, 'https://img.pccomponentes.com/articles/25/251622/1291-aoc-cu34g2x-bk-34-led-wide-quadhd-144hz-freesync-curvo-comprar.jpg', 3),
(27, 'Acer EI322QUR P 31.5\" LED QHD 165Hz FreeSync 2 Curva', 'Elija un monitor que lo atraiga al juego con una pantalla curva WQHD (2560x1440). Disfrutará de cuadros de alta velocidad y sin rasgaduras que estallarán en colores realistas a través de la tecnología Radeon FreeSync™ 2 HDR de AMD. Y con su diseño ZeroFrame, hay mucha pantalla para que la disfrutes.', 249, 0, 'https://img.pccomponentes.com/articles/1072/10722579/1345-acer-ei322q-315-led-qhd-165hz-freesync-2-curva.jpg', 3),
(28, 'HP OMEN 34c 34\" WQHD 165Hz FreeSync Premium Curva', 'No hay nada mejor que evadirse por completo dentro del juego. El monitor gaming curvo OMEN by HP de 34 pulgadas, WQHD y 165 Hz es sinónimo de inmersión gracias a su pantalla curva ultrapanorámica de 1500R.\r\n\r\nSu excelente rendimiento y el realismo de sus colores HDR permiten que pueda perderse en los juegos que más le gustan. Además, su diseño elegante se ve, suena y queda estupendamente sobre su escritorio.', 389, 0, 'https://img.pccomponentes.com/articles/1073/10734319/1175-hp-omen-34c-34-wqhd-165hz-freesync-premium-curva.jpg', 3),
(29, 'MSI Optix G321CU 31.5\" LED UltraHD 4K 144Hz FreeSync Premium Curva', 'Visualiza tu victoria con el monitor MSI G321CU Curved Gaming™. Equipado con un panel 4K-UHD 3840 x 2160, frecuencia de actualización de 144 Hz, tiempo de respuesta de 1 ms, G321CU le brindará la ventaja competitiva que necesita para derrotar a sus oponentes. Construido con AMD FreeSync Premium, G321CU puede igualar la frecuencia de actualización de la pantalla con su GPU para un juego ultra fluido. ', 549, 0.11, 'https://img.pccomponentes.com/articles/1065/10650206/1197-msi-optix-g321cu-315-led-ultrahd-4k-144hz-freesync-premium-curva.jpg', 3),
(30, 'MSI MAG 321QR-QD 31.5\" LED IPS WQHD 170Hz G-Sync Compatible', 'La serie MAG lucha junto a los jugadores en busca del honor. Con elementos de inspiración militar agregados en estos productos de juego, renacieron como el símbolo de robustez y durabilidad. Una reproducción de color de nivel de creación de contenido de 98 % Adobe RGB, 97 % DCI-P3 y 81 % REC.2020 mediante el uso de una capa diseñada dinámicamente de nanopartículas Quantum Dot. ', 369, 0.32, 'https://img.pccomponentes.com/articles/1066/10661908/112-msi-mag-321qr-qd-315-led-ips-wqhd-170hz-g-sync-compatible.jpg', 3),
(31, 'Apple iPhone 15 128GB Rosa Libre', 'Apple iPhone 15. La Dynamic Island a la cabeza de todo. Nueva cámara de 48 Mpx. Fotos de altísima resolución. Teleobjetivo x2. Diseño superduro con aluminio y vidrio tintado en masa. El USB C llega a puerto.', 889, 0, 'https://img.pccomponentes.com/articles/1077/10777746/1798-apple-iphone-15-128gb-rosa-libre.jpg', 4),
(32, 'Apple iPhone 15 Pro Max 256GB Titanio Azul Libre', 'iPhone 15 Pro Max. Forjado en titanio y con el revolucionario chip A17 Pro, un botón Acción personalizable y el sistema de cámaras más potente que haya tenido un iPhone.', 1399, 0.5, 'https://img.pccomponentes.com/articles/1077/10777887/158-apple-iphone-15-pro-max-256gb-titanio-azul-libre.jpg', 4),
(33, 'Apple iPhone 15 Pro Max 512GB Titanio Blanco Libre', 'iPhone 15 Pro Max. Forjado en titanio y con el revolucionario chip A17 Pro, un botón Acción personalizable y el sistema de cámaras más potente que haya tenido un iPhone.', 1599, 0, 'https://img.pccomponentes.com/articles/1077/10777896/1350-apple-iphone-15-pro-max-512gb-titanio-blanco-libre.jpg', 4),
(34, 'Realme C33 4/64GB Dorado Libre', 'El realme C33 es el único teléfono en su rango de precios con cámara AI de 50 MP y, en comparación con el realme C35, el realme C33 de 50 MP también utiliza el nuevo algoritmo CHDR para tomas con luz de fondo más claras.\r\n\r\nAdemás de la continuación del diseño de bisel en ángulo recto del realme C35, el realme C33 también presenta un nuevo diseño de mar sin límites que tiene un grosor de 8,3 mm.', 99, 0, 'https://img.pccomponentes.com/articles/1066/10663035/1260-realme-c33-4-64gb-dorado-libre.jpg', 4),
(35, 'Realme 11 Pro 5G 8/256GB Amoled FHD+ Curved Sunrise Beige Libre', 'El nuevo smartphone realme 11 Pro 5G incorpora una pantalla OLED de 6,7\" y resolución FHD+, el procesador MediaTek Dimensity 7050 de ocho núcleos, una batería de 5000 mAh con carga rápida de 67 W y una cámara principal de 100 MP junto con otra de 16 MP para selfies.', 279, 0.5, 'https://img.pccomponentes.com/articles/1073/10732197/1612-realme-11-pro-5g-8-256gb-sunrise-beige-libre.jpg', 4),
(36, 'Samsung Galaxy A14 4/128GB Verde Libre', 'Galaxy A14 es un smartphone increíblemente versátil y asequible, diseñado para adaptarse a tus necesidades diarias. Con su gran pantalla de alta calidad, podrás disfrutar de tus contenidos multimedia favoritos con la mejor resolución y calidad de sonido gracias a Dolby Atmos. La cámara principal de alta resolución te permitirá capturar momentos increíbles.', 167, 0, 'https://img.pccomponentes.com/articles/1067/10676781/1479-samsung-galaxy-a14-4-128gb-verde-libre.jpg', 4),
(37, 'Samsung Galaxy S22 5G 256GB Rosa Dorado Libre', 'Samsung Galaxy S22 5G. Un nuevo épico estándar para cada momento en tu vida, con pantalla Dynamic AMOLED x2 de 6.1\" FHD+, 4 cámaras de alta resolución y calidad profesional, un procesador todopoderoso de solo 4 nm y un atrevido diseño Premium con 4 nuevos colores.', 586, 0.16, 'https://img.pccomponentes.com/articles/1063/10638247/1806-samsung-galaxy-s22-5g-256gb-rosa-dorado-libre.jpg', 4),
(38, 'Samsung Galaxy S23 Ultra 256GB Algodón Libre', 'Samsung Galaxy S23 Ultra supera los límites de la categoría Premium en todo sentido y te permite demostrarle al mundo de lo que eres capaz de hacer. Descubre una configuración de 5 cámaras épicas y un rendimiento sorprendente que no te abandonará nunca. Transforma este dispositivo en un ordenador cuando lo necesites con la ayuda de Samsung Dex y lleva tus proyectos contigo donde sea que vayas. Galaxy S23 Ultra fue diseñado para aquellos que viven una vida llena de momentos increíbles .', 1011, 0.23, 'https://img.pccomponentes.com/articles/1067/10670308/1724-samsung-galaxy-s23-ultra-256gb-algodon-libre.jpg', 4),
(39, 'Samsung Galaxy S21 Ultra 5G 128GB Negro Libre', 'Galaxy S21 evoluciona la cámara hasta el nivel más avanzado en un Galaxy. Un diseño único centrado en realzar sus lentes unido a la mejor tecnología del mercado y funciones para que sacarle el máximo partido esté al alcance de todos.', 515, 0, 'https://img.pccomponentes.com/articles/34/348320/1247-samsung-galaxy-s21-ultra-5g-128gb-negro-libre.jpg', 4),
(40, 'LG OLED42C34LA 42\" OLED evo UltraHD 4K HDR10', 'Entra en el mundo del realismo en el televisor LG OLED42C34LA. El portal hacia un nuevo universo de experiencias inmersivas. Conviértete en el protagonista gracias a las imágenes excepcionales y el sonido refinado que el Procesador de Máxima Potencia 4K ?9 Gen 6 con IA puede proporcionar.', 949, 0.29, 'https://img.pccomponentes.com/articles/1071/10718154/1506-lg-oled42c34la-42-oled-evo-ultrahd-4k-hdr10-322859c1-4f05-4dc8-9ffa-22d14a964e6b.jpg', 5),
(41, 'Philips 42OLED818 42\" OLED UltraHD 4K HDR10+', 'De películas a programas, la imagen realista y el sonido con cuerpo de este televisor OLED se combinan con el halo inmersivo de Ambilight para brindarle una experiencia excepcional. El soporte pulido y delgado como una navaja y el control remoto con respaldo de cuero aportan elegancia.', 999, 0.16, 'https://img.pccomponentes.com/articles/1073/10737908/1543-philips-42oled818-42-oled-ultrahd-4k-hdr10.jpg', 5),
(42, 'Philips 43PUS7608/12 43\" LED UltraHD 4K HDR10+', 'Te guste lo que te guste, este Smart TV con gran capacidad de respuesta lo encontrará rápido. Desde clásicos favoritos hasta nuevos lanzamientos, deja que el televisor Philips PUS7608 busque por ti en todos los grandes servicios de streaming y disfruta de una imagen nítida y un sonido Dolby Atmos envolvente.', 300, 0, 'https://img.pccomponentes.com/articles/1073/10733622/1907-philips-43pus7608-12-led-ultrahd-4k-hdr10.jpg', 5),
(43, 'LG OLED48C26LB 48\" OLED EVO UltraHD 4K HDR10 Pro', 'Televisor LG 4K OLED evo: el único negro puro que hace que el resto de colores brille, gracias a sus más de 33 millones de puntos autoluminiscentes. Con procesador de máxima potencia 4K a9 Gen 5 con IA basada en Deep Learning.', 879, 0.1, 'https://img.pccomponentes.com/articles/1063/10634350/1513-lg-oled48c26lb-48-oled-evo-ultrahd-4k-hdr10-pro.jpg', 5),
(44, 'LG 65NANO766QA 65\" LED NanoCell UltraHD 4K HDR10 Pro', 'Procesador Inteligente* de Gran Potencia 4K a5 Gen 5. Disfruta de colores más vivos. El procesador de Gran Potencia 4K a5 Gen 5 ofrece una gran precisión de tonos y colores, ya que actúa sobre 576 áreas de cada fotograma. Además, es capaz de controlar el color y el brillo e identifica el movimiento de los objetos para escalar y simular un Sonido Surround de 5.1 canales.', 649, 0.1, 'https://img.pccomponentes.com/articles/1024/10241277/1484-lg-65nano766qa-65-led-nanocell-ultrahd-4k-hdr10-pro-foto.jpg', 5),
(45, 'LG 75NANO816QA 75\" LED Nanocell UltraHD 4K HDR10 Pro', 'Ecosistema Abierto e Inteligente (ThinQ) & webOS. Un TV para que disfrutes de tu hogar. Los TVs NanoCell de LG cuentan con WebOS22, más fácil, intuitivo y seguro, que permite crear perfiles por usuario y disfrutar así sólo del contenido que tú elijas. Además, podrás controlar todos tus dispositivos inteligentes y tu televisor NanoCell de LG.', 994, 0, 'https://img.pccomponentes.com/articles/1040/10401502/3952-lg-75nano816qa-75-led-nanocell-ultrahd-4k-hdr10-pro-mejor-precio.jpg', 5),
(46, 'Nilait Prisma NI-32HB7001S 32\" LED HD Ready HDR10 Smart TV', 'Experimenta ahora todo el contenido con Nilait Prisma NI-32HB7001S LED HD Ready Smart TV, que incluye una pantalla con resolución HD, así como tecnologías de pantalla y audio que harán que disfrutes de una experiencia de uso excelente.', 157, 0, 'https://img.pccomponentes.com/articles/1065/10657766/1465-prisma-32-hd-ready-smart-comprar.jpg', 5),
(47, 'Nilait Prisma 24HB7001N 24\" ELED HD Ready Conector 12V', 'Descubre la TV Nilait Prisma NI-24HB7001N-12VDC ELED HD Ready, diseñada para acompañarte en tus aventuras en autocaravanas, coches, caravanas y embarcaciones. Disfruta del entretenimiento en cualquier lugar con resolución HD y adaptador 12V para una experiencia de uso excelente allá donde vayas.', 120, 0.05, 'https://img.pccomponentes.com/articles/1066/10667188/1708-nilait-prisma-24hb7001n-24-eled-hd-ready-conector-12v.jpg', 5),
(48, 'Hisense SBS RS650N4AC2 Frigorífico Americano E Acero Inoxidable', 'Hisense SBS RS650N4AC2 es un frigorífico americano con un volumen total de 500L, acabado en acero inoxidable y eficiencia energética E.', 729, 0.05, 'https://img.pccomponentes.com/articles/38/380832/1832-hisense-sbs-rs650n4ac2-frigorifico-americano-e-acero-inoxidable-foto.jpg', 6),
(49, 'Origial COOL AND FREEZE SBS Frigorífico Americano No Frost E Acero Inoxidable', 'Olvida los problemas de espacio gracias a su amplio almacenamiento. Mantén tus alimentos frescos y ordenados de manera sencilla en sus estanterías regulables, cajones y balcones', 499, 0, 'https://img.pccomponentes.com/articles/1065/10650221/1444-origial-cool-and-freeze-sbs-frigorifico-americano-no-frost-e-acero-inoxidable-5e680fba-f186-4041-9217-a35f9663ab98.jpg', 6),
(50, 'Roborock Q5 Pro+ Robot Aspirador Negro + Estación de Vaciado Automático', 'El Roborock Q5 Pro+ es un robot aspirador, fregona y mopa 3 en 1 que ofrece una limpieza eficiente y completa de su hogar. Cuenta con un potente motor de succión de 5500 Pa que elimina el polvo, el pelo de las mascotas y la suciedad de las alfombras y los suelos duros. También cuenta con un sistema de fregado húmedo que ayuda a eliminar las manchas y la suciedad. ', 469, 0.34, 'https://img.pccomponentes.com/articles/1079/10790485/1114-roborock-q5-pro-robot-aspirador-negro-estacion-de-vaciado-automatico.jpg', 6),
(51, 'Xiaomi Mi Robot Vacuum S10 Robot Aspirador Blanco', 'El Xiaomi Robot Vacuum S10 es una aspiradora robot con potencia de succión de 4000 Pa y tanque de agua inteligente que se adapta al tipo de suelo. Tanto su sistema de navegación láser como los múltiples sensores integrados consiguen un funcionamiento efectivo, con un control sencillo y totalmente personalizado a través de la aplicación móvil Xiaomi Home.', 247, 0.1, 'https://img.pccomponentes.com/articles/1073/10735900/1833-xiaomi-mi-robot-vacuum-s10-robot-aspirador-blanco.jpg', 6),
(52, 'Origial FRYFIT 3.5L Freidora de Aire 3.5L 1200W Negra', 'La freidora de aire Origial FRYFIT 3,5L dispone de una capacidad de 3,5 litros, el tamaño idóneo para atender las necesidades de un hogar con hasta cuatro personas.\r\n\r\nCuenta con 8 funciones predefinidas que establecen de manera automática el tiempo y la temperatura según el tipo de alimento, asegurando así una cocción impecable.', 46, 0, 'https://img.pccomponentes.com/articles/1070/10709153/1239-origial-fryfit-35l-freidora-de-aire-35l-1200w-negra.jpg', 6),
(53, 'Cosori Dual Blaze Chef Edition Freidora de Aire 6.4L 1700W Negra', 'La freidora de aire Cosori Dual Blaze Chef Edition incorpora una novedad con respecto a los anteriores modelos: su doble resistencia, que emite calor por arriba y por abajo, permitiendo una cocción uniforme y garantizando la circulación del aire 360º por ambos lados. Su tecnología 360 ThermoIQ evita que haya que remover los alimentos o precalentar el dispositivo.', 179, 0, 'https://img.pccomponentes.com/articles/1063/10633848/1787-cosori-dual-blaze-chef-edition-freidora-de-aire-64l-1700w-negra.jpg', 6),
(54, 'Tristar FR-9008PR Freidora de Aire 4.2L 1500W', 'Con la freidora de aire Tristar FR-9008PR podrá freír, asar y hornear sus tentempiés y platos favoritos sin utilizar aceite, por lo que disfrutará de platos más saludables y menos olores. Incluye temporizador y termostato ajustable.', 49, 0.08, 'https://img.pccomponentes.com/articles/1073/10738986/1238-tristar-fr-9008pr-freidora-de-aire-42l-1500w.jpg', 6),
(55, 'Origial ORIWM9BW Prowash 9 KG Inverter Lavadora de Carga Frontal 9Kg B', 'Incorpora la función de limpieza a vapor para desinfectar en profundidad. El vapor penetra en las fibras de los tejidos profundamente, removiendo los malos olores y dejando la colada perfecta como el primer día', 289, 0.07, 'https://img.pccomponentes.com/articles/1058/10582834/1168-origial-oriwm9bw-prowash-9-kg-inverter-lavadora-de-carga-frontal-9kg-b.jpg', 6),
(56, 'Candy CSO 14105TE/1-S Lavadora Carga Frontal 10Kg E Blanca', 'Candy CSO 14105TE/1-S es una lavadora de carga frontal con una capacidad de 10Kg y eficiencia energética E. Dispone de conectividad WiFi y Bluetooth, y acabado en color blanco.', 289, 0, 'https://img.pccomponentes.com/articles/34/347550/1530-candy-cso-14105te-1-s-lavadora-carga-frontal-10kg-a-blanca-caracteristicas.jpg', 6),
(57, 'Samsung WW80CGC04DTHEC Lavadora Carga Frontal 8Kg A Blanca', 'SmartThings AI Energy Mode: Ahorra energía de una forma inteligente. Controla y reduce el consumo de energía de la lavadora. Energy Mode te permite comprobar el consumo de energía y calcular tu factura de la luz. Este modo reduce el uso de energía hasta un 70%, utilizando Ecobubble™ para lavar con agua fría en lugar de caliente y un tiempo de ciclo extra.', 335, 0.2, 'https://img.pccomponentes.com/articles/1079/10791760/1752-samsung-ww80cgc04dthec-lavadora-carga-frontal-8kg-a-blanca.jpg', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntuacion`
--

CREATE TABLE `puntuacion` (
  `id` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `puntuacion` int(11) NOT NULL DEFAULT 3 CHECK (`puntuacion` between 1 and 5),
  `producto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `puntuacion`
--

INSERT INTO `puntuacion` (`id`, `fecha`, `puntuacion`, `producto_id`) VALUES
(1, '2024-01-17', 1, 1),
(2, '2024-01-17', 2, 2),
(3, '2024-01-17', 4, 3),
(4, '2024-01-17', 4, 4),
(5, '2024-01-17', 2, 5),
(6, '2024-01-17', 3, 6),
(7, '2024-01-17', 1, 7),
(8, '2024-01-17', 5, 8),
(9, '2024-01-17', 3, 9),
(10, '2024-01-17', 2, 10),
(11, '2024-01-17', 5, 11),
(12, '2024-01-17', 4, 12),
(13, '2024-01-17', 1, 13),
(14, '2024-01-17', 3, 14),
(15, '2024-01-17', 2, 15),
(16, '2024-01-17', 5, 16),
(17, '2024-01-17', 4, 17),
(18, '2024-01-17', 1, 18),
(19, '2024-01-17', 3, 19),
(20, '2024-01-17', 2, 20),
(21, '2024-01-17', 5, 21),
(22, '2024-01-17', 4, 22),
(23, '2024-01-17', 1, 23),
(24, '2024-01-17', 3, 24),
(25, '2024-01-17', 2, 25),
(26, '2024-01-17', 5, 26),
(27, '2024-01-17', 4, 27),
(28, '2024-01-17', 1, 28),
(29, '2024-01-17', 3, 29),
(30, '2024-01-17', 2, 30),
(31, '2024-01-17', 5, 31),
(32, '2024-01-17', 4, 32),
(33, '2024-01-17', 1, 33),
(34, '2024-01-17', 3, 34),
(35, '2024-01-17', 2, 35),
(36, '2024-01-17', 5, 36),
(37, '2024-01-17', 4, 37),
(38, '2024-01-17', 1, 38),
(39, '2024-01-17', 3, 39),
(40, '2024-01-17', 2, 40),
(41, '2024-01-17', 5, 41),
(42, '2024-01-17', 4, 42),
(43, '2024-01-17', 1, 43),
(44, '2024-01-17', 3, 44),
(45, '2024-01-17', 2, 45),
(46, '2024-01-17', 5, 46),
(47, '2024-01-17', 4, 47),
(48, '2024-01-17', 1, 48),
(49, '2024-01-17', 3, 49),
(50, '2024-01-17', 2, 50),
(51, '2024-01-17', 5, 51),
(52, '2024-01-17', 4, 52),
(53, '2024-01-17', 1, 53),
(54, '2024-01-17', 3, 54),
(55, '2024-01-17', 2, 55),
(56, '2024-01-17', 5, 56),
(57, '2024-01-17', 4, 57),
(58, '2024-01-17', 1, 1),
(59, '2024-01-17', 4, 37),
(60, '2024-01-17', 3, 20),
(61, '2024-01-17', 5, 15),
(62, '2024-01-17', 1, 16),
(63, '2024-01-17', 4, 3),
(64, '2024-01-17', 5, 11),
(65, '2024-01-17', 1, 53),
(66, '2024-01-17', 4, 10),
(67, '2024-01-17', 4, 57),
(68, '2024-01-17', 4, 9),
(69, '2024-01-17', 5, 15),
(70, '2024-01-17', 3, 32),
(71, '2024-01-17', 4, 55),
(72, '2024-01-17', 1, 7),
(73, '2024-01-17', 4, 7),
(74, '2024-01-17', 3, 15),
(75, '2024-01-17', 5, 30),
(76, '2024-01-17', 4, 55),
(77, '2024-01-17', 3, 2),
(78, '2024-01-17', 1, 11),
(79, '2024-01-17', 4, 4),
(80, '2024-01-17', 5, 53),
(81, '2024-01-17', 1, 17),
(82, '2024-01-17', 4, 18),
(83, '2024-01-17', 3, 36),
(84, '2024-01-17', 4, 50),
(85, '2024-01-17', 2, 30),
(86, '2024-01-17', 5, 12),
(87, '2024-01-17', 1, 53),
(88, '2024-01-17', 2, 42),
(89, '2024-01-17', 2, 40),
(90, '2024-01-17', 2, 9),
(91, '2024-01-17', 3, 7),
(92, '2024-01-17', 3, 40),
(93, '2024-01-17', 2, 49),
(94, '2024-01-17', 4, 33),
(95, '2024-01-17', 1, 33),
(96, '2024-01-17', 5, 42),
(97, '2024-01-17', 5, 28),
(98, '2024-01-17', 3, 52),
(99, '2024-01-17', 3, 56),
(100, '2024-01-17', 4, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_id`);

--
-- Indices de la tabla `puntuacion`
--
ALTER TABLE `puntuacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `puntuacion`
--
ALTER TABLE `puntuacion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `puntuacion`
--
ALTER TABLE `puntuacion`
  ADD CONSTRAINT `puntuacion_ibfk_1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
