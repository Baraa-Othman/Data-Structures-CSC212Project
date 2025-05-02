public class Test {

    public static void main(String[] args) {
        PhotoManager manager = new PhotoManager();
        Album album1 = new Album("Album1", "animal AND based", manager);
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        Photo photo4 = new Photo("fish.jpg", toTagsLinkedList("animal, water, blue, sea, wave"));
        Photo photo5 = new Photo("cat.jpg", toTagsLinkedList("animal, cat, pet, cute"));
        Photo photo6 = new Photo("dog.jpg", toTagsLinkedList("animal, dog, pet, cute"));
        Photo photo7 = new Photo("bird.jpg", toTagsLinkedList("animal, bird, pet, cute"));
        Photo photo8 = new Photo("elephant.jpg", toTagsLinkedList("animal, elephant, big, gray"));
        Photo photo9 = new Photo("lion.jpg", toTagsLinkedList("animal, lion, big, yellow"));
        

        System.out.println(album1.getPhotos());
        InvIndexPhotoManager invIndexManager = new InvIndexPhotoManager();
        invIndexManager.addPhoto(photo1);
        invIndexManager.addPhoto(photo2);
        invIndexManager.addPhoto(photo3);
        invIndexManager.addPhoto(photo4);
        invIndexManager.addPhoto(photo5);
        invIndexManager.addPhoto(photo6);
        invIndexManager.addPhoto(photo7);
        invIndexManager.addPhoto(photo8);
        invIndexManager.addPhoto(photo9);
        manager.addPhoto(photo1);
        manager.addPhoto(photo2);
        manager.addPhoto(photo3);
        manager.addPhoto(photo4);
        manager.addPhoto(photo5);
        manager.addPhoto(photo6);
        manager.addPhoto(photo7);
        manager.addPhoto(photo8);
        manager.addPhoto(photo9);
       System.out.println("Manager photos:");
        System.out.println(manager.getPhotos());
        System.out.println("InvIndexManager photos:");
        invIndexManager.getPhotos();
        System.out.println("Get album1 name, condition, and photos:");
        System.out.println("album1 name: " + album1.getName());
        System.out.println("album1 condition: " + album1.getCondition());
        System.out.println("Get album1 photos:");
        System.out.println(album1.getPhotos());
        System.out.println("Number of comparisons in album1: " + album1.getNbComps());
        manager.deletePhoto("bear.jpg");
        manager.deletePhoto("hedgehog.jpg");
        System.out.println("Manager photos after deletion:");
        System.out.println(manager.getPhotos());
        
        System.out.println("InvIndexManager photos after deletion:");
        invIndexManager.deletePhoto("bear.jpg");
        invIndexManager.deletePhoto("hedgehog.jpg");
        invIndexManager.deletePhoto("orange-butterfly.jpg");
        invIndexManager.getPhotos();
        }
        private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (int i = 0; i < tagsArray.length; i++) {
        result.insert(tagsArray[i]);
        }
        return result;
        }
    
}
