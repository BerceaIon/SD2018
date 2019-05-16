	<%@ include file="common/header.jspf" %>
	<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Available books are</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.desc}</td>
						<td><fmt:formatDate value="${book.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${book.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-book?id=${book.id}">Borrow</a></td>
							
							
						<td><a type="button" class="btn btn-warning"
							href="/delete-book?id=${book.id}">Delete</a></td> 
							
							
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div>
			<a class="button" href="/add-book">Add a book</a>
		</div>
		
	</div>
	
	<%@ include file="common/footer.jspf" %>