package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<String> getAllLocalizations(){
		String sql = "select distinct Localization from classification";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getString("Localization"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Adiacenza> getAllAdiacenze(){
		String sql = "select c1.Localization as l1, c2.Localization as l2, i.Type as tipo "
				+ "from classification c1, classification c2, interactions i "
				+ "where c1.GeneID = i.GeneID1 AND c2.GeneID = i.GeneID2 and c1.Localization != c2.Localization";
		List<Adiacenza> result = new LinkedList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Adiacenza a = new Adiacenza(res.getString("l1"), res.getString("l2"));
				String tipo = res.getString("tipo");
				a.addTipo(tipo);
				if (result.contains(a)) {
					if (!result.get(result.indexOf(a)).getTipi().contains(tipo)) {
						result.get(result.indexOf(a)).addTipo(tipo);
					}
				} else {
					result.add(a);
				}
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
}
