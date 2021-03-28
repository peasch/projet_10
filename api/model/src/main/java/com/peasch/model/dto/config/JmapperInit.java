package com.peasch.model.dto.config;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Author.AuthorDto;
import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.Borrowings.BorrowingDto;
import com.peasch.model.dto.Borrowings.BorrowingLateDTO;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.Categories.CategoryDto;
import com.peasch.model.dto.Role.RoleDto;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.User.UserWithAllDTO;
import com.peasch.model.dto.User.UserWithRoleDTO;
import com.peasch.model.dto.WaitList.WaitListWithAllDto;
import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.model.dto.libraries.LibraryDto;
import com.peasch.model.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmapperInit {
    @Bean
    JMapper<BookDto, Book> bookToDTOMapper() {
        return new JMapper<>(BookDto.class, Book.class);
    }

    @Bean
    JMapper<Book, BookDto> dtoToBookMapper() {
        return new JMapper<>(Book.class, BookDto.class);
    }

    @Bean
    JMapper<BookWithoutCopiesDTO, Book> bookWithoutCopiesDTOBookJMapper() {
        return new JMapper<>(BookWithoutCopiesDTO.class, Book.class);
    }

    @Bean
    JMapper<AuthorDto, Author> authorToDTOMapper() {
        return new JMapper<>(AuthorDto.class, Author.class);
    }

    @Bean
    JMapper<Author, AuthorDto> dtoToAuthorMapper() {
        return new JMapper<>(Author.class, AuthorDto.class);
    }
//---------------------COPIES------------------------

    @Bean
    JMapper<CopyDto, Copy> copyToDTOMapper() {
        return new JMapper<>(CopyDto.class, Copy.class);
    }

    @Bean
    JMapper<Copy, CopyDto> dtoToCopyMapper() {
        return new JMapper<>(Copy.class, CopyDto.class);
    }

    @Bean
    JMapper<CopyWithALLDTO, Copy> copyWithAllToDTOMapper() {
        return new JMapper<>(CopyWithALLDTO.class, Copy.class);
    }

    @Bean
    JMapper<Copy, CopyWithALLDTO> dtoToCopyWithAllMapper() {
        return new JMapper<>(Copy.class, CopyWithALLDTO.class);
    }

    // ----------------- USER-----------------------
    @Bean
    JMapper<UserDto, User> userToDTOMapper() {
        return new JMapper<>(UserDto.class, User.class);
    }

    @Bean
    JMapper<User, UserDto> dtoToUserMapper() {
        return new JMapper<>(User.class, UserDto.class);
    }

    @Bean
    JMapper<UserWithRoleDTO, User> userWithRoleToDTOMapper() {
        return new JMapper<>(UserWithRoleDTO.class, User.class);
    }

    @Bean
    JMapper<User, UserWithRoleDTO> dtoToUseWithRoleMapper() {
        return new JMapper<>(User.class, UserWithRoleDTO.class);
    }

    @Bean
    JMapper<UserWithAllDTO, User> userWithAllToDTOMapper() {
        return new JMapper<>(UserWithAllDTO.class, User.class);
    }

    @Bean
    JMapper<User, UserWithAllDTO> dtoToUseWithAllMapper() {
        return new JMapper<>(User.class, UserWithAllDTO.class);
    }

    //-------------------------- ROLE-----------------------
    @Bean
    JMapper<RoleDto, Role> roleToDTOMapper() {
        return new JMapper<>(RoleDto.class, Role.class);
    }

    @Bean
    JMapper<Role, RoleDto> dtoToRoleMapper() {
        return new JMapper<>(Role.class, RoleDto.class);
    }

    @Bean
    JMapper<CategoryDto, Category> categoryToDTOMapper() {
        return new JMapper<>(CategoryDto.class, Category.class);
    }

    @Bean
    JMapper<Category, CategoryDto> dtoToCategoryMapper() {
        return new JMapper<>(Category.class, CategoryDto.class);
    }

    //----------------------BORROWINGS-------------------
    @Bean
    JMapper<BorrowingDto, Borrowing> borrowingToDTOMapper() {
        return new JMapper<>(BorrowingDto.class, Borrowing.class);
    }

    @Bean
    JMapper<Borrowing, BorrowingDto> dtoToBorrowingMapper() {
        return new JMapper<>(Borrowing.class, BorrowingDto.class);
    }

    @Bean
    JMapper<BorrowingWithAllDTO, Borrowing> borrowingWithAllToDTOMapper() {
        return new JMapper<>(BorrowingWithAllDTO.class, Borrowing.class);
    }

    @Bean
    JMapper<Borrowing, BorrowingWithAllDTO> dtoToBorrowingWithAllMapper() {
        return new JMapper<>(Borrowing.class, BorrowingWithAllDTO.class);
    }

    @Bean
    JMapper<BorrowingLateDTO, Borrowing> borrowingLateToDTOMapper() {
        return new JMapper<>(BorrowingLateDTO.class, Borrowing.class);
    }

    @Bean
    JMapper<Borrowing, BorrowingLateDTO> dtoToBorrowingLateMapper() {
        return new JMapper<>(Borrowing.class, BorrowingLateDTO.class);
    }


    @Bean
    JMapper<LibraryDto, Library> libraryToDTOMapper() {
        return new JMapper<>(LibraryDto.class, Library.class);
    }

    @Bean
    JMapper<Library, LibraryDto> dtoToLibraryMapper() {
        return new JMapper<>(Library.class, LibraryDto.class);
    }

    //----------------------------------WaitList------------------------------------------

    @Bean
    JMapper<WaitListWithAllDto, WaitListDemand> waitListToDTOMapper() {
        return new JMapper<>(WaitListWithAllDto.class,WaitListDemand.class);
    }

    @Bean
    JMapper<WaitListDemand, WaitListWithAllDto> dtoToWaitListMapper() {
        return new JMapper<>(WaitListDemand.class, WaitListWithAllDto.class);
    }
}
