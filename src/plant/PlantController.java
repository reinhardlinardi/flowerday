package plant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Kelas real yang mengurus tentang kelakuan pot dan bunga semasa hidupnya.
 *
 * @author Gisela Supardi
 * @version 23/04/2017
 */
public class PlantController extends Thread {
  /** Pot yang diurus oleh controller ini. */
  private Pot potInControl;
  /** Bunga yang diurus oleh controller ini. */
  private Flower flowerInControl;
  /** Representasi tanaman yang diurus controller ini pada layar. */
  private PlantView viewInControl;
  /** Default waktu perpindahan state pertumbuhan dalam ms. */
  private final int GROWTH_TIME = 3000;
  /** Default batas waktu dalam ms untuk memanen bunga sebelum membusuk. */
  private final int HARVEST_TIME = 3000;

  /** Konstruktor. */
  public PlantController() {
    potInControl = new Pot();
    viewInControl = new PlantView();
  }

  /**
   * Menginisialisasi <code>Flower</code> yang ada pada controller ini.
   *
   * @param flowerName nama bunga yang ingin dimasukkan
   */
  public void addFlowerToPot(String flowerName) {
    switch (flowerName) {
      case "sunflower":
        flowerInControl = new Sunflower();
        break;
      case "rose":
        flowerInControl = new Rose();
        break;
      case "orchid":
        flowerInControl = new Orchid();
        break;
      case "chamomile":
        flowerInControl = new Chamomile();
        break;
      default:
        // do nothing
    }
  }

  /**
   * Getter untuk harga beli bunga.
   *
   * @param name nama bunga yang ingin diketahui namanya
   * @return harga dari bunga berjenis <code>name</code>
   */
  public int getFlowerPrice(String name) {
    Flower dummy;
    switch (name) {
      case "sunflower":
        dummy = new Sunflower();
        break;
      case "rose":
        dummy = new Rose();
        break;
      case "orchid":
        dummy = new Orchid();
        break;
      default:
        dummy = new Chamomile();
    }
    return dummy.getPrice();
  }

  /**
   * Fungsi untuk mendapatkan nilai jual bunga saat dipanen.
   *
   * @return harga jual bunga
   */
  public int harvest() {
    return flowerInControl.getSellingPrice();
  }

  public void growTheFlower() {
    switch (flowerInControl.getFlowerState()) {
      case 1:
        //fall through karena aksi yang dilakukan sama dengan 2
      case 2:
        Timer growingTimer = new Timer();
        TimerTask growingTask = new TimerTask() {
          @Override
          public void run() {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= GROWTH_TIME) {
              // wait for growing delay
            }
            growingTimer.cancel();
          }
        };
        growingTimer.schedule(growingTask, 30);
        flowerInControl.grow();
        break;
      case 3:
        Timer harvestTimer = new Timer();
        TimerTask harvestTask = new TimerTask() {
          @Override
          public void run() {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= HARVEST_TIME) {
              // wait for growing delay
            }
            harvestTimer.cancel();
          }
        };
        harvestTimer.schedule(harvestTask, 30);
        flowerInControl.grow();
        break;
      default:
        // bunga busuk; do nothing
    }
  }
}