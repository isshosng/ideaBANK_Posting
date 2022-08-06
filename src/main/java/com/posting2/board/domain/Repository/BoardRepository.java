package com.posting2.board.domain.Repository;

import com.posting2.board.domain.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}

/* Repository는 데이터 조작을 담당하며, JpaRepository를 상속받습니다.
JpaRepository의 값을 매핑할 Entity와 Id 타입입니다.
 */

