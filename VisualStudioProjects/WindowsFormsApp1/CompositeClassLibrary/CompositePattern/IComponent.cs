namespace CompositeClassLibrary.CompositePattern
{
    public interface IComponent
    {
        string Name { get; set; }

        void Add(IComponent component);
        //void Remove(IComponent component);
        //IComponent GetChild(int index);
        void Draw(System.Drawing.Graphics graphics);
    }
}