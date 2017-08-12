/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.shared.service;

/**
 *
 * @author jose2
 */
public interface Repositorio<T> {

    public boolean add(T obj);

    public T find(int id);
}
