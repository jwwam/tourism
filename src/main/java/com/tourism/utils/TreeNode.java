package com.tourism.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiexingbao
 * 递归tree
 */
public class TreeNode {

	private String id;//id

	private String parentId;//父类id

	private String name;//名字
	
    private String href = "#";
    
    private String alias = "";
    
    private boolean spread = false;
    
	
	private int type = 1;//1是目录

	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode(){}
	
	public TreeNode(String id, String parentId, String name, String fdcMemo,boolean spread) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.spread = spread;
	}

	public void add(TreeNode node) {

		// 递归添加节点
		if ( "0".equals(node.parentId) ) {

			this.children.add(node);

		} else if ( node.parentId.equals(this.id) ) {

			this.children.add(node);

		} else {

			for (TreeNode tmp_node : children) {
				tmp_node.add(node);
			}

		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	
}
