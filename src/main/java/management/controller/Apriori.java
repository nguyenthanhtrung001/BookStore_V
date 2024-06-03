package management.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import management.dao.IAprioriDao;

import management.entity.Ctpd;
import management.entity.Mathang;
import management.entity.Phieudat;

//import javax.management.loading.PrivateClassLoader;

@Component
public class Apriori {

	@Autowired
	private IAprioriDao aprioriDao;

	private static int numTransactions = 0;
	private static int minSup = 3;
	private static double maxConfidence = 65.0;
	private static int maxSize = 1;
	private static int sizeCustomer = 0;
	private static int sizeAll = 0;

	public List<Mathang> Apriori(int makh) {
//        Integer kiemtra=hoaDonService.getLaySP_KIEMTRAHD(masothue);
//        if (kiemtra==0) {
//			List<Mathang> mathangs=new ArrayList<Mathang>();
//			return null;
//		}
		List<Ctpd> listcthd = aprioriDao.getLayDSCTHD(makh);
		List<Phieudat> listhoadon = aprioriDao.getLayDSHD(makh);
		List<Mathang> listmathangAll = aprioriDao.getLayDSSP();
		List<Mathang> listmathangBuy = aprioriDao.getLayDSSPDAMUA(makh);

		
		String data[][] = new String[200][200];

		String frequentItemSet[][] = new String[1000][1000];
		int Location[] = new int[1000];
		String itemHistory[] = new String[1000];
		String itemAll[] = new String[1000];

		List<String> finaly = new ArrayList<String>();
		LoadProductAll(itemAll, listmathangAll);
		LoadProductCustomer(itemHistory, listmathangBuy);
		LOADDATA(listhoadon, listcthd, data);
		foundFrequentItemSet(data, frequentItemSet, Location, itemAll);
	    foundLawDetermination(frequentItemSet,data,finaly,0.6);


		List<Mathang> listmathang = new ArrayList<Mathang>();
		List<Double> avgList = new ArrayList<Double>();
		for (int i = 0; i < sizeCustomer; i++) {
			Double danhgia = aprioriDao.getLAYDANHGIA(Integer.parseInt(itemHistory[i]));
			avgList.add(danhgia);
		}
		bubbleSort1(itemHistory, avgList);
		if (finaly.size() > 1) {
			avgList.clear();
			for (int i = 0; i < finaly.size(); i++) {
				Double danhgia = aprioriDao.getLAYDANHGIA(Integer.parseInt(finaly.get(i)));
				avgList.add(danhgia);
			}
			bubbleSort(finaly, avgList);
			int dem = 0;
			if (finaly.size() > 5) {
				for (int i = 0; i < 5; i++)
				// goi sp
				{
					Mathang mathang = aprioriDao.getMHById(Integer.parseInt(finaly.get(i)));
					listmathang.add(mathang);
				}
			} else {
				for (int i = 0; i < finaly.size(); i++)
				// goi sp
				{
//					dem++;
					Mathang mathang = aprioriDao.getMHById(Integer.parseInt(finaly.get(i)));
					listmathang.add(mathang);
				}
				if (sizeCustomer > 5 - finaly.size())
					for (int i = finaly.size(); i < 5; i++) {
						Mathang mathang = aprioriDao.getMHById(Integer.parseInt(itemHistory[i]));
						listmathang.add(mathang);
						dem++;
					}
				else {
					for (int i = finaly.size(); i < sizeCustomer; i++) {
						Mathang mathang = aprioriDao.getMHById(Integer.parseInt(itemHistory[i]));
						listmathang.add(mathang);
						dem++;
					}
				}
			}
		} else {
			if (finaly.size() == 1) {
				String cleanedString = finaly.get(0).trim().replace(",", "");

				Mathang mathang = aprioriDao.getMHById(Integer.parseInt(cleanedString));
				listmathang.add(mathang);
				if (sizeCustomer > 4)
					for (int i = 0; i < 4; i++) {
						mathang = aprioriDao.getMHById(Integer.parseInt(itemHistory[i]));
						listmathang.add(mathang);
					}
				else
					for (int i = 0; i < sizeCustomer; i++) {
						mathang = aprioriDao.getMHById(Integer.parseInt(itemHistory[i]));
						listmathang.add(mathang);
					}
			} else {
				if (sizeCustomer > 5)
					for (int i = 0; i < 5; i++) {
						Mathang mathang = aprioriDao.getMHById(Integer.parseInt(itemHistory[i]));
						listmathang.add(mathang);
					}
				else
					for (int i = 0; i < sizeCustomer; i++) {
						Mathang mathang = aprioriDao.getMHById(Integer.parseInt(itemHistory[i]));
						listmathang.add(mathang);
					}
			}
		}
//		System.out.println("In list mat hang");

//		for(Mathang mathang : listmathang) {
//		System.out.println(mathang.getMamh());
//		}
//		System.out.println("ket thuc list mat hang");
		return listmathang;

	}

	// Lấy danh sách các sản phẩm đã mua của all
	public void LoadProductAll(String itemAll[], List<Mathang> mh) {
		int i = 0;
		for (Mathang m : mh) {
			itemAll[i] = m.getMamh() + "";
			i++;
		}
		sizeAll = i;
	}

	// Lấy danh sách sản phẩm mà khách hàng mua
	public void LoadProductCustomer(String itemHistory[], List<Mathang> mh) {
		int i = 0;
		for (Mathang m : mh) {
			itemHistory[i] = m.getMamh() + "";
			i++;
		}
		sizeCustomer = i;
	}

//	@Query(value = "CALL SP_LAYDSHD(:masothue)", nativeQuery = true)
	public void LOADDATA(List<Phieudat> HD, List<Ctpd> cthd, String data[][]) {
		int i = 0, j = 0;
		numTransactions = HD.size();
		int max = 1;
		for (Phieudat hd : HD) {
			data[i][j] = hd.getMapd() + "";
			j++;
			for (Ctpd ct : cthd) {
				if (ct.getPhieudat().getMapd() == hd.getMapd()) {
					data[i][j] = ct.getId().getMamh() + "";
					j++;
				}
			}
			if (j > max)
				max = j;
			j = 0;
			i++;
		}
		maxSize = max;
	}

	// ok
	// Tìm tập phổ biến

	public static void foundFrequentItemSet(String data[][], String frequentItemSet[][], int Location[],
			String itemAll[]) {
		int count = 0;
//		int n = 0;
		String tmp;
		int rows = 0;
		int colums = 0;
		List<String> frequentTmp = new ArrayList<String>();
		// Tìm tập phổ biến 1 thuộc tính.
		// String B[] = { "A", "B", "C", "D", "E" };
		for (int i = 0; i < sizeAll; i++) {
			tmp = itemAll[i];
			// tmp = B[i];
			for (int j = 0; j < numTransactions; j++) {
				for (int k = 1; k < maxSize; k++) {
					// if(A[j][k] == null) break;
					if (tmp.equals(data[j][k])) {
						count++;
						break;
					}
				}
			}
			if (count > minSup || count == minSup) {
				frequentItemSet[rows][colums] = tmp;
				Location[rows] = count;
				rows++;
				count = 0;
				frequentTmp.add(tmp);
			}

		}
		colums++;
		int rowXet = 0;
		int rowXet1 = 0;
		boolean cons = true;
//		for(String s: frequentTmp)
//			System.out.print(s +" ");
//		System.out.println();
		frequentTmp.remove(0);
//		for(String s: frequentTmp)
//			System.out.print(s +" ");
//		int xet = 0;
//		int daXet = 0;
		int columsXet = 1;
		int dem = 0;
//		int numCol = numTransactions;
		while (cons) {
			if (rows == 0)
				break;
			// Duyệt hết một phần tử.
			for (int t = 0; t < frequentTmp.size(); t++) {
				// Duyệt một phần tử
				for (int p = t; p < frequentTmp.size(); p++) {
					count = 0;
					// Kiểm tra một dòng.
					for (int i = 0; i < numTransactions; i++) {
						// Kiểm tra A có phải là con của B?
						for (int k = 0; k < columsXet; k++) {
							for (int j = 1; j < maxSize; j++) {
								if (frequentItemSet[rowXet][k].equals(data[i][j])) {
									dem++;
									break;
								}
							}

						}
						// kiểm tra phần tử mới đã tồn tại chưa
						for (int j = 1; j < maxSize; j++)
							if (frequentTmp.get(p).equals(data[i][j])) {
								dem++;
								break;
							}
						if (dem == columsXet + 1)
							count++;
						dem = 0;
					}
					// Kiểm tra mức độ phổ biến
					if (count > minSup || count == minSup) {
						for (int i = 0; i < columsXet + 1; i++) {
							if (i == columsXet) {
								frequentItemSet[rows][i] = frequentTmp.get(p);
								break;
							}
							frequentItemSet[rows][i] = frequentItemSet[rowXet][i];
						}
						Location[rows] = count;
						rows++;
						columsXet++;
						rowXet = rows - 1;
					} else
						break;
				}
				rowXet = rowXet1;
				columsXet = 1;
			}
			rowXet1++;
			rowXet = rowXet1;
			columsXet = 1;
			// xảy ra lỗi!
			frequentTmp.remove(0);
//			if(frequentTmp.size() == 1)
			if (frequentTmp.size() == 0)
				cons = false;
		}
		System.out.println("hello");

	}
	 public static void foundLawDetermination(String frequentItemSet[][], String data[][],List<String> finaly, double minConfidence) {
	        int numRows = data.length;
	        Set<String>locfinaly= new HashSet<>();
	        for (int i = 0; i < frequentItemSet.length; i++) {
	            List<String> items = new ArrayList<>();
	            for (int j = 0; j < frequentItemSet[i].length; j++) {
	                if (frequentItemSet[i][j] != null) {
	                    items.add(frequentItemSet[i][j]);
	                }
	            }

	            if (items.size() >= 2) {
	                generateAssociationRules(items, frequentItemSet, data, numRows, minConfidence,locfinaly);
	            }
	        }
	        finaly= new ArrayList<>(locfinaly);
	        System.out.println("Tập kết quả:");
	        for( String x: finaly)
	        {
	        	System.out.println(x);
	        }
	        System.out.println("Tập kết quả:");
	        System.out.println("hello");
	    }

	    private static void generateAssociationRules(List<String> items, String frequentItemSet[][], String data[][], int numRows, double minConfidence,Set<String> finaly) {
	        
	    	
	    	
	    	int n = items.size();
	        for (int i = 1; i < (1 << n) - 1; i++) {
	            List<String> antecedent = new ArrayList<>();
	            List<String> consequent = new ArrayList<>();

	            for (int j = 0; j < n; j++) {
	                if ((i & (1 << j)) > 0) {
	                    antecedent.add(items.get(j));
	                } else {
	                    consequent.add(items.get(j));
	                }
	            }

	            double supportAntecedent = calculateSupport(antecedent, frequentItemSet, data, numRows);
	            double supportTotal = calculateSupport(items, frequentItemSet, data, numRows);

	            if (supportTotal > 0) {
	                double confidence = supportTotal / supportAntecedent;
	                if (confidence >= minConfidence) {
	                	for( String x:consequent) {
	                		finaly.add(x);
	                	}
	                    System.out.print(antecedent + " => " + consequent);
	                    System.out.println(" (Confidence: " + confidence + ")=("+supportAntecedent+"/"+supportTotal+")");
	                }
	            }
	        }
	      
	       
	    }

	    private static double calculateSupport(List<String> items, String frequentItemSet[][], String data[][], int numRows) {
	        int count = 0;
	        for (int i = 0; i < numRows; i++) {
	            boolean containsAll = true;
	            for (String item : items) {
	                if (!containsItem(data[i], item)) {
	                    containsAll = false;
	                    break;
	                }
	            }
	            if (containsAll) {
	                count++;
	            }
	        }
	        return (double) count / numRows;
	    }

	    private static boolean containsItem(String row[], String item) {
	        for (int i = 1; i < row.length; i++) {
	            if (item.equals(row[i])) {
	                return true;
	            }
	        }
	        return false;
	    }
	

		public void bubbleSort(List<String> finaly, List<Double> avg) {
		Double tmp1;
		String tmp;
		int i, j;
		boolean swapped;
		for (i = 0; i < finaly.size() - 1; i++) {
			swapped = false;
			for (j = 0; j < finaly.size() - 1 - i; j++)
				if (avg.get(j) < avg.get(j + 1)) {
					tmp1 = avg.get(j);
					tmp = finaly.get(j);
					avg.set(j, avg.get(j + 1));
					avg.set(j + 1, tmp1);
					finaly.set(j, finaly.get(j + 1));
					finaly.set(j + 1, tmp);
					swapped = true;
				}
			if (!swapped)
				break;
		}

	}

	public void bubbleSort1(String itemHistory[], List<Double> avg) {
		Double tmp1;
		String tmp;
		int i, j;
		boolean swapped;
		if (sizeCustomer == 0)
			return;
		for (i = 0; i < sizeCustomer - 1; i++) {
			swapped = false;
			for (j = 0; j < sizeCustomer - 1 - i; j++)
				if (avg.get(j) < avg.get(j + 1)) {
					tmp1 = avg.get(j);
					tmp = itemHistory[j];
					avg.set(j, avg.get(j + 1));
					avg.set(j + 1, tmp1);
					itemHistory[j] = itemHistory[j + 1];
					itemHistory[j + 1] = tmp;
					swapped = true;
				}
			if (!swapped)
				break;
		}
	}
}
